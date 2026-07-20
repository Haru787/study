const searchBtn      = document.getElementById("searchBtn");
const orderIdInput   = document.getElementById("orderIdInput");
const detailPanel    = document.getElementById("detailPanel");
const errorMsg       = document.getElementById("errorMsg");
const itemsBody      = document.getElementById("itemsBody");

function showError(msg) {
  errorMsg.textContent = msg;
  errorMsg.style.display = "block";
}

function hideError() {
  errorMsg.style.display = "none";
}

function formatCurrency(amount) {
  return amount.toLocaleString() + "円";
}

function renderOrderDetail(order) {
  document.getElementById("dispOrderId").textContent        = order.orderId;
  document.getElementById("dispCustomerName").textContent   = order.customerName;
  document.getElementById("dispOrderDate").textContent      = order.orderDate;
  document.getElementById("dispShippingAddress").textContent = order.shippingAddress;
  document.getElementById("dispStatus").textContent         = order.status;

  itemsBody.innerHTML = "";
  let total = 0;

  order.items.forEach(item => {
    const subtotal = item.unitPrice * item.quantity;
    total += subtotal;

    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${item.productId}</td>
      <td>${item.productName}</td>
      <td class="num">${formatCurrency(item.unitPrice)}</td> //構文エラーitem.unitPrise → item.unitPriceに修正
      <td class="num">${item.quantity}</td>
      <td class="num">${formatCurrency(subtotal)}</td>
    `;
    itemsBody.appendChild(row);
  });

  const taxRate = order.taxRate || 10;
  const totalWithTax = Math.round(total * (1 + taxRate / 100));
  document.getElementById("dispTotal").textContent = formatCurrency(totalWithTax);
}

searchBtn.addEventListener("click", async () => {
  const orderId = orderIdInput.value.trim();
  hideError();
  detailPanel.style.display = "none";

  if (orderId === "") {
    showError("注文IDを入力してください");
    return;
  }

  try {
    const response = await fetch("/api/orders/" + encodeURIComponent(orderId));

    if (response.status === 404) {
      showError("注文ID「" + orderId + "」は見つかりませんでした");
      return;
    }

    if (!response.ok) {
      throw new Error("サーバーエラー: " + response.status);
    }

    const order = await response.json();
    renderOrderDetail(order);
    detailPanel.style.display = "block";

  } catch (error) {
    console.error("エラー:", error);
    showError("注文情報の取得に失敗しました。しばらく経ってから再度お試しください。");
  }
});