const workList = document.getElementById("wklst");

// ============= work event handler =========================
async function handleWorkRegister(e) {
    const formdata = getEditFormData("editWk");
    const response = await workRegister(formdata);
    console.log(response.text());
}

async function handleWorkRemove(e) {
    const id = e.target.dataset.wkId
    const response = await workRemove(id);
    console.log(response.text());
}

async function handleWorkModify(e) {
    const formdata = getEditFormData("editWk");
    const response = await workModify(formdata);
    console.log(response.text());
}

async function handleWorkRankUp(e) {
    const id = e.target.dataset.wkId
    const response = await workRankUp(id);
    console.log(response.text());
}

async function handleWorkRankDown(e) {
    const id = e.target.dataset.wkId
    const response = await workRankDown(id);
    console.log(response.text());
}

async function handleChangeParent(e)
    const parentEl = document.querySelector(`[data-changeParent]`).dataset.wkId;
    const currentEl = document.querySelector(`[data-changeWkId]`).dataset.wkId;
    const response = await workChangeParent(parentEl, currentEl);
    console.log(response.text());
}

// ========== edited form data ===================
function getEditFormData(editName) {
    // FormData 객체 생성
    const formData = new FormData();

    // 특정 data-속성을 가진 모든 input 요소 선택
    const inputs = document.querySelectorAll(`input[data-${editName}]`);

    // 선택된 input 요소 순회
    inputs.forEach(input => {
        // data 속성의 값이 키가 되고 input의 값이 FormData에 추가됨
        const key = input.name ;
        const value = input.value;

        // FormData에 키-값 추가
        formData.append(key, value);
    });
    return formData;
}
