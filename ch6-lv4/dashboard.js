const loadBtn        = document.getElementById("loadBtn");
const dashboardPanel = document.getElementById("dashboardPanel");
const errorMsg       = document.getElementById("errorMsg");
const loadingMsg     = document.getElementById("loadingMsg");

function formatCurrency(amount) {
  return amount.toLocaleString() + "円";
}

function formatPercent(value) {
  return (Math.round(value * 10) / 10).toFixed(1) + "%";
}

loadBtn.addEventListener("click", async () => {
  errorMsg.style.display   = "none";
  dashboardPanel.style.display = "none";
  loadingMsg.style.display = "block";

  try {
    const response = await fetch("/api/sales/monthly-summary");

    if (!response.ok) {
      throw new Error("取得失敗: " + response.status);
    }

    const data = await response.json();

    document.getElementById("totalSales").textContent =
      formatCurrency(data.totalSales);

    document.getElementById("orderCount").textContent =
      data.orderCount + "件";

    document.getElementById("avgAmount").textContent =
      formatCurrency(Math.round(data.totalSales / data.orderCount));

    document.getElementById("newMembers").textContent =
      data.newMembers + "人"; //タイポ修正

    const tbody = document.getElementById("categoryRanking");
    tbody.innerHTML = "";

    data.categoryRanking.forEach((item, index) => {
      const ratio = (item.amount / data.totalSales) * 100;
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${index + 1}</td>
        <td>${item.categoryName}</td>
        <td class="num">${formatCurrency(item.amount)}</td> //item.sales → item.amountに修正
        <td class="num">${formatPercent(ratio)}</td>
      `;
      tbody.appendChild(row);
    });

    loadingMsg.style.display = "none";
    dashboardPanel.style.display = "block";

  } catch (error) {
    console.error("エラー:", error);
    errorMsg.textContent = "データの取得に失敗しました。";
    errorMsg.style.display = "block";
    loadingMsg.style.display = "none";
  }
});