const form            = document.getElementById("orderForm");
const resultMsg       = document.getElementById("resultMsg");

function getVal(id) {
  return document.getElementById(id).value.trim();
}

function showFieldError(id) {
  document.getElementById(id).style.display = "inline";
}

function hideFieldError(id) {
  document.getElementById(id).style.display = "none";
}

function clearAllErrors() {
  ["errCustomerName", "errProductId", "errQuantity", "errShippingAddress"].forEach(id => {
    hideFieldError(id);
  });
}

function validate() {
  let valid = true;

  const customerName = getVal("customerName");
  if (customerName === "") {
    showFieldError("errCustomerName");
    valid = false;
  }

  const productId = getVal("productId");
  if (productId === "") {
    showFieldError("errProductId")
    valid = false;
  }

  const quantityStr = getVal("quantity");
  const quantity = parseInt(quantityStr, 10);
  if (isNaN(quantity) || quantity < 1) {
    showFieldError("errQuantity");
    valid = false;
  }

  const shippingAddress = getVal("shippingAddress");
  if (shippingAddress === ""){ //'{'追加
    showFieldError("errShippingAddress");
    valid = false;
  }

  return valid;
}

form.addEventListener("submit", async (event) => {
  event.preventDefault();
  clearAllErrors();
  resultMsg.style.display = "none";

  if (!validate()) return;

  const requestBody = {
    customerName:    getVal("customerName"),
    productId:       getVal("productId"),
    quantity:        parseInt(getVal("quantity"), 10),
    shippingAddress: getVal("shippingAddress"),
    note:            getVal("note")
  };

  try {
    const response = await fetch("/api/orders", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody)
    });

    if (!response.ok) {
      throw new Error("注文登録失敗: " + response.status);
    }

    const result = await response.json();
    resultMsg.textContent = "注文が確定されました。注文ID：" + result.orderId;
    resultMsg.className = "success";
    resultMsg.style.display = "block";
    form.reset();

  } catch (error) {
    console.error("エラー:", error);
    resultMsg.textContent = "注文の登録に失敗しました。しばらく経ってから再度お試しください。";
    resultMsg.className = "failure";
    resultMsg.style.display = "block";
  }
});