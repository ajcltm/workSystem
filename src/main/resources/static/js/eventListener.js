const workList = document.getElementById("wklst");




// ============= workInfo event handler =========================

async function handleWorkInfo(id) {
    const response = await workInfo(id);
    const html = await response.text()
    console.log("handleWorkInfo");
    console.log("html : " + html);
    document.getElementById("workInfo").innerHTML = html;
}

// ============= work event handler =========================
async function handleWorkRegister(e) {
    const formdata = getEditFormData("wkForm");
    const response = await workRegister(formdata);

    console.log("handleWorkRegister");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleWorkRemove(e) {
    const id = e.target.dataset.wkId
    const response = await workRemove(id);

    console.log("handleWorkRemove");
    console.log("dataset wkId : " + id);
    console.log(response.text());
}

async function handleWorkModify(e) {
    const formdata = getEditFormData("wkForm");
    const response = await workModify(formdata);

    console.log("handleWorkModify");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleWorkRankUp(e) {
    const id = e.target.dataset.wkId
    const response = await workRankUp(id);

    console.log("handleWorkRankUp");
    console.log("dataset wkId : " + id);
    console.log(response.text());
}

async function handleWorkRankDown(e) {
    const id = e.target.dataset.wkId
    const response = await workRankDown(id);

    console.log("handleWorkRankDown");
    console.log("dataset wkId : " + id);
    console.log(response.text());
}

async function handleChangeParent(e){
    const parentId = document.querySelector(`[data-changeParent]`).dataset.wkId;
    const currentId = document.querySelector(`[data-changeWkId]`).dataset.wkId;
    const response = await workChangeParent(parentEl, currentEl);

    console.log("handleChangeParent");
    console.log("dataset changeParent : " + parentId);
    console.log("dataset changeWkId : " + currentId);
    console.log(response.text());
}

// ============= logInfo event handler =========================

async function handleLogInfo(e) {
    const id = e.target.dataset.wkId;
    const response = await logInfo(id);

    console.log("handleLogInfo");
    console.log("dataset wkId : " + id);
    return response.text();
}


// ============= log event handler =========================

async function handleLogRegister(e) {
    const formdata = getEditFormData("lgForm");
    const response = await logRegister(formdata);

    console.log("handleLogRegister");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleLogRemove(e) {
    const id = e.target.dataset.lgId
    const response = await logRemove(id);

    console.log("handleLogRemove");
    console.log("dataset lgId : " + id);
    console.log(response.text());
}

async function handleLogModify(e) {
    const formdata = getEditFormData("lgForm");
    const response = await logModify(formdata);

    console.log("handleLogModify");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

// ============= stakeholder event handler =========================

async function handleStakeholderRegister(e) {
    const formdata = getEditFormData("shForm");
    const response = await stakeholderRegister(formdata);

    console.log("handleStakeholderRegister");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleStakeholderRemove(e) {
    const id = e.target.dataset.shId
    const response = await stakeholderRemove(id);

    console.log("handleStakeholderRemove");
    console.log("dataset shId : " + id);
    console.log(response.text());
}

async function handleStakeholderModify(e) {
    const formdata = getEditFormData("shForm");
    const response = await stakeholderModify(formdata);

    console.log("handleStakeholderModify");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

// ============= stakeholder event handler =========================

async function handleFileUpload(e) {
    const formdata = getEditFormData("wfForm");
    const response = await fileUpload(formdata);

    console.log("handleFileUpload");
    console.log(response.text());
}

async function handleFileDownload(e) {
    const id = e.target.dataset.wfId
    const response = await fileDownload(id);

    console.log("handleFileDownload");
    console.log("dataset wfId : " + id);
    console.log(response.text());
}

// ========== form data ===================
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
