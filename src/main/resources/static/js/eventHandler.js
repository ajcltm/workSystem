const workList = document.getElementById("wklst");




// ============= workInfo event handler =========================

async function handleWorkInfo(id) {
    const response = await workInfo(id);
    const html = await response.text()
    console.log("handleWorkInfo");
    document.getElementById("workInfo").innerHTML = html;
    arrangeDisplay();
    levelize();
    addEventListener_bl_data_work();
}

// ============= work event handler =========================
async function handleWorkRegister(e) {
    const closestBlDataItem = e.target.closest('.bl_data_item_box_edit');

    // 특정 data-속성을 가진 모든 input 요소 선택
    const inputs = closestBlDataItem.querySelectorAll(`input[data-wk-edit]`);
    console.log(inputs)
    const formdata = getEditFormData(inputs);
    const response = await workRegister(formdata);

    console.log("handleWorkRegister");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleWorkRemove(e) {
    const wkId = e.target.closest(".bl_data_item").dataset.wkId;
    const response = await workRemove(wkId);
    console.log("handleWorkRemove");
    console.log("dataset wkId : " + wkId);
    console.log(response.text());
}

function handleVisible(e) {
    const closestBlDataItem = e.target.closest('.bl_data_item');

    if (closestBlDataItem) {

        closestBlDataItem.classList.toggle("bl_data_item_edit");
        // 해당 .bl_data_item 내에서 .bl_data_item_subBox_edit와 .bl_data_item_edit_box 찾기
        const subBoxEdit = closestBlDataItem.querySelector('.bl_data_item_subBox_edit');
        const boxEdit = closestBlDataItem.querySelector('.bl_data_item_box_edit');

        const subBox = closestBlDataItem.querySelector('.bl_data_item_subBox');
        const box = closestBlDataItem.querySelector('.bl_data_item_box');

        // hp_unVisible 클래스 토글
        if(subBoxEdit){
            subBoxEdit.classList.toggle('hp_unVisible');
        }
        boxEdit.classList.toggle('hp_unVisible');

        if(subBox) {
            subBox.classList.toggle('hp_unVisible');
        }
        box.classList.toggle('hp_unVisible');
    }
}

async function handleWorkModify(e) {

    const closestBlDataItem = e.target.closest('.bl_data_item');
    console.log("handleWorkModify");

    // 특정 data-속성을 가진 모든 input 요소 선택
    const inputs = closestBlDataItem.querySelectorAll(`input[data-wk-edit]`);
    console.log(inputs)
    const formdata = getEditFormData(inputs);
    const response = await workModify(formdata);

    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleWorkFileUpLoad(e) {

    const closestBlDataItem = e.target.closest('.bl_data_item');
    console.log("handleFileUpLoad");

    const inputs_file = closestBlDataItem.querySelectorAll(`input[data-wk-file-edit]`);
    console.log("inputs_file"+inputs_file)
    const formdata_file = getEditFileFormData(inputs_file);
    if (([...formdata_file.keys()].length === 0)) {
        console.log("empty file")
        return ;
    }
    const response_file = await fileUpload(formdata_file);

    console.log("formdata : " + formdata_file);
    console.log(response_file.text());
}

async function handleWorkRankUp(e) {
    const response = await workRankUp(wkId);

    console.log("handleWorkRankUp");
    console.log("dataset wkId : " + wkId);
    console.log(response.text());
}

async function handleWorkRankDown(e) {
    const response = await workRankDown(wkId);

    console.log("handleWorkRankDown");
    console.log("dataset wkId : " + wkId);
    console.log(response.text());
}

async function handleChangeParent(e){
    const parentId = e.target.closest(".bl_data_item").dataset.wkRank;
    const currentId = wkId;
    const response = await workChangeParent(parentId, currentId);

    console.log("handleChangeParent");
    console.log("dataset changeParent : " + parentId);
    console.log("dataset changeWkId : " + currentId);
    console.log(response.text());
}

async function handleRegisterChild(e) {

    const closestBlDataItem = e.target.closest('.bl_data_item');
    console.log("handleRegisterChild");

    // 특정 data-속성을 가진 모든 input 요소 선택
    const inputs = closestBlDataItem.querySelectorAll(`input[data-wk-edit]`);
    console.log(inputs)

    // 오늘 날짜를 yyyy-MM-dd 형식으로 가져오기
    const today = new Date();
    const formattedToday = today.toISOString().split('T')[0];

    // wkRank 요소의 value를 저장 (단일 요소가 있다고 가정)
    let wkRankValue = null;
    inputs.forEach(input => {
        if (input.name === "wkRank") {
            wkRankValue = input.value;
        }
    });

    // 조건에 맞지 않는 요소 제거 및 값 업데이트
    inputs.forEach(input => {
        if (input.name === "wkRepDate" || input.name === "wkDueDate") {
            input.value = formattedToday; // 오늘 날짜로 설정
        } else if (input.name === "wkParent" && wkRankValue !== null) {
            input.value = wkRankValue; // wkRank의 value로 설정
        } else if (input.name !== "wkRepDate" && input.name !== "wkDueDate" && input.name !== "wkParent") {
            // 조건에 맞지 않는 요소 삭제
            input.parentNode.removeChild(input);
        }
    });

    const formdata = getEditFormData(inputs);
    const response = await workRegister(formdata);

    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleRemoveParent(e){
    const currentId = wkId;
    const response = await workRemoveParent(currentId);

    console.log("handleChangeParent");
    console.log("dataset changeWkId : " + currentId);
    console.log(response.text());
}



// ============= logInfo event handler =========================

async function handleLogInfo(e) {
    // const wkId = e.target.closest(".bl_data_item").dataset.wkId;

    console.log(`wkId: ${wkId}`);
    const response = await logInfo(wkId);
    const html = await response.text()
    console.log("handleLogInfo");
    document.getElementById("bl_lg_data").innerHTML = html;
    await addEventListener_bl_data_log();
}


// ============= log event handler =========================

async function handleLogRegister(e) {
    const closestBlDataItem = e.target.closest('.bl_data_item_box_edit');

    // 특정 data-속성을 가진 모든 input 요소 선택
    const inputs = closestBlDataItem.querySelectorAll(`input[data-lg-edit]`);
    console.log(inputs)

    const formdata = getEditFormData(inputs);
    const response = await logRegister(formdata);

    console.log("handleLogRegister");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleLogRemove(e) {
    const id = e.target.closest(".bl_data_item").dataset.lgId;
    const response = await logRemove(id);

    console.log("handleLogRemove");
    console.log("dataset lgId : " + id);
    console.log(response.text());
}

async function handleLogModify(e) {
    const closestBlDataItem = e.target.closest('.bl_data_item');
    console.log("handleWorkModify");

    // 특정 data-속성을 가진 모든 input 요소 선택
    const inputs = closestBlDataItem.querySelectorAll(`input[data-lg-edit]`);
    console.log(inputs)
    const formdata = getEditFormData(inputs);
    const response = await logModify(formdata);

    console.log("handleLogModify");
    console.log("formdata : " + formdata);
    console.log(response.text());
}


async function handleLogFileUpLoad(e) {

    const closestBlDataItem = e.target.closest('.bl_data_item');
    console.log("handleLogFileUpLoad");

    const inputs_file = closestBlDataItem.querySelectorAll(`input[data-lg-file-edit]`);
    console.log("inputs_file"+inputs_file)
    const formdata_file = getEditFileFormData(inputs_file);
    if (([...formdata_file.keys()].length === 0)) {
        return ;
    }
    const response_file = await fileUpload(formdata_file);

    console.log("formdata : " + formdata_file);
    console.log(response_file.text());
}

// ============= stakeholder event handler =========================


async function handleStakeholderInfo(e) {
    // const wkId = e.target.closest(".bl_data_item").dataset.shId;
    console.log(`wkId: ${wkId}`);
    const response = await stakeholderInfo(wkId);
    const html = await response.text()
    console.log("handleStakeholderInfo");
    document.getElementById("bl_sh_data").innerHTML = html;
    addEventListener_bl_data_stakeholder();
}

async function handleStakeholderRegister(e) {
    const closestBlDataItem = e.target.closest('.bl_data_item_box_edit');

    // 특정 data-속성을 가진 모든 input 요소 선택
    const inputs = closestBlDataItem.querySelectorAll(`input[data-sh-edit]`);
    console.log(inputs)
    const formdata = getEditFormData(inputs);
    const response = await stakeholderRegister(formdata);

    console.log("handleStakeholderRegister");
    console.log("formdata : " + formdata);
    console.log(response.text());
}

async function handleStakeholderRemove(e) {
    const id = e.target.closest(".bl_data_item").dataset.shId;
    const response = await stakeholderRemove(id);

    console.log("handleStakeholderRemove");
    console.log("dataset shId : " + id);
    console.log(response.text());
}

async function handleStakeholderModify(e) {

    const closestBlDataItem = e.target.closest('.bl_data_item');
    console.log("handleShModify");

    // 특정 data-속성을 가진 모든 input 요소 선택
    const inputs = closestBlDataItem.querySelectorAll(`input[data-sh-edit]`);
    console.log(inputs)
    const formdata = getEditFormData(inputs);
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
function getEditFormData(inputs) {
    // FormData 객체 생성
    const formData = new FormData();

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

function getEditFileFormData(inputs) {
    console.log("getEditFileFormData");
    // FormData 객체 생성
    const formData = new FormData();

    // 선택된 input 요소 순회
    inputs.forEach(input => {
        // data 속성의 값이 키가 되고 input의 값이 FormData에 추가됨
        const wfWkId = input.dataset.wfWkId;
        const wfLgId = input.dataset.wfLgId;
        if (input.files.length === 0) {
            console.log("empty file length!");
            return ;
        }
        // FormData에 키-값 추가
        Array.from(input.files).forEach(file => {
            formData.append("files", file); // 'files' 키로 파일 추가
        });
        formData.append("wfWkId", wfWkId);
        formData.append("wfLgId", wfLgId);
    });
    return formData;
}
