const loadBtn      = document.getElementById("loadBtn");
const searchBtn    = document.getElementById("searchBtn");
const searchInput  = document.getElementById("searchInput");
const tableBody    = document.getElementById("productTableBody");
const errorMsg     = document.getElementById("errorMsg");
const loadingMsg   = document.getElementById("loadingMsg");

let allProducts = [];

function showError(message) {
  errorMsg.textContent = message;
  errorMsg.style.display = "block";
}

function hideError() {
  errorMsg.style.display = "none";
}

function showLoading() {
  loadingMsg.style.display = "block";
}

function hideLoading() {
  loadingMsg.style.display = "none";
}

function renderTable(products) {
  tableBody.innerHTML = "";
  if (products.length === 0) {
    const row = document.createElement("tr");
    row.innerHTML = '<td colspan="5" style="text-align:center;">該当する商品がありません</td>';
    tableBody.appendChild(row);
    return;
  }
  products.forEach(product => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${product.productId}</td>
      <td>${product.name}</td>
      <td>${product.category}</td>
      <td>${product.price.toLocaleString()}円</td>
      <td>${product.stock}個</td>
    `;
    tableBody.appendChild(row);
  });
}

async function fetchAllProducts() {
  hideError();
  showLoading();
  tableBody.innerHTML = "";

  try {
    const response = await fetch("/api/products");

    if (!response.ok) {
      throw new Error("サーバーエラー: " + response.status);
    }

    allProducts = await response.json()

    renderTable(allProducts);

} catch (error) {//エラー修正："}"を追加。
    console.error("取得エラー:", error);
    showError("商品一覧の取得に失敗しました。しばらく経ってから再度お試しください。");
  } finally {
    hideLoading();
  }
}

function filterProducts(keyword) {
  const trimmed = keyword.trim().toLowerCase();
  if (trimmed === "") {
    renderTable(allProducts);
    return;
  }
  const filtered = allProducts.filter(p =>
    p.name.toLowerCase().includes(trimmed) ||
    p.category.toLowerCase().includes(trimmed)
  );
  renderTable(filtered);
}

loadBtn.addEventListener("click", () => {
  fetchAllProducts();
});

searchBtn.addEventListener("click", () => {
  filterProducts(searchInput.value);
});

searchInput.addEventListener("keydown", (event) => {
  if (event.key === "Enter") {
    filterProducts(searchInput.value);
  }
});