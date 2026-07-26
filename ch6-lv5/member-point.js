const searchBtn   = document.getElementById("searchBtn");
const addBtn      = document.getElementById("addBtn");
const useBtn      = document.getElementById("useBtn");
const memberPanel = document.getElementById("memberPanel");
const searchError = document.getElementById("searchError");
const successMsg  = document.getElementById("successMsg");
const actionError = document.getElementById("actionError");

let currentMemberId = null;

function showMsg(el, text) {
  el.textContent = text;
  el.style.display = "block";
}

function hideAll() {
  [searchError, successMsg, actionError].forEach(el => el.style.display = "none");
}

function renderMember(member) {
  document.getElementById("dispMemberId").textContent = member.memberId;
  document.getElementById("dispName").textContent     = member.name;
  document.getElementById("dispRank").textContent     = member.rank;
  document.getElementById("dispPoints").textContent   = member.currentPoints.toLocaleString() + "pt";
  document.getElementById("dispLifetime").textContent = member.lifetimePoints.toLocaleString() + "pt";
  memberPanel.style.display = "block";
}

searchBtn.addEventListener("click", async () => {
  hideAll();
  memberPanel.style.display = "none";
  currentMemberId = null;

  const memberId = document.getElementById("memberIdInput").value.trim();
  if (memberId === "") {
    showMsg(searchError, "会員IDを入力してください");
    return;
  }

  try {
    const response = await fetch("/api/members/" + encodeURIComponent(memberId));

    if (response.status === 404) {
      showMsg(searchError, "会員ID「" + memberId + "」は見つかりませんでした");
      return;
    }
    if (!response.ok) throw new Error("サーバーエラー: " + response.status)

    const member = await response.json();
    currentMemberId = member.memberId;
    renderMember(member);

  } catch (error) {
    console.error("検索エラー:", error);
    showMsg(searchError, "検索に失敗しました。しばらく経ってから再度お試しください。");
  }
});

async function updatePoints(action) {
  hideAll();

  if (!currentMemberId) {
    showMsg(actionError, "先に会員を検索してください");
    return;
  }

  const pointStr = document.getElementById("pointInput").value;
  const points   = parseInt(pointStr, 10);

  if (isNaN(points) || points <= 0) {
    showMsg(actionError, "1以上のポイント数を入力してください");
    return;
  }

  try {
    const pointsToSend = action === "use" ? -points : points; //利用時はマイナスの値を渡す。
    const response = await fetch("/api/members/" + currentMemberId + "/points", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ action: action, points: pointsToSend })
    });

    if (!response.ok) {
      const err = await response.json().catch(() => ({}));
      throw new Error(err.message || "更新失敗: " + response.status);
    }

    const updated = await response.json();
    renderMember(updated);

    const label = action === "add" ? "付与" : "利用";
    showMsg(successMsg, points + "pt を" + label + "しました（残高：" + updated.currentPoints.toLocaleString() + "pt）");
    document.getElementById("pointInput").value = "";

  } catch (error) {
    console.error("ポイント更新エラー:", error);
    showMsg(actionError, "ポイント更新に失敗しました: " + error.message);
  }
}

addBtn.addEventListener("click", () => updatePoints("add"));
useBtn.addEventListener("click", () => updatePoints("use"));