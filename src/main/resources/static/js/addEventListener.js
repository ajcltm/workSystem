
function addEventListener_bl_data_work() {
    const bl_data = document.querySelector("#bl_wk_data");

    bl_data.removeEventListener('click', handle_bl_data_work_Click);

    // 이벤트 등록
    bl_data.addEventListener('click', handle_bl_data_work_Click);
}


async function handle_bl_data_work_Click(e) {

    console.log("click bl_data");

    selectWkId(e);

    const bl_data_item_box = e.target.closest('.bl_data_item_box');
    const bl_data_item_subBox = e.target.closest('.bl_data_item_subBox');

    if (bl_data_item_box) {
        console.log("click bl_data_item_box")
        await handleLogInfo(e);
        await handleStakeholderInfo(e);
        // await addEventListener_bl_data_log();
        // await addEventListener_bl_data_stakeholder();
    }

    if (bl_data_item_subBox) {
        console.log("click bl_data_item_box")
        await handleLogInfo(e);
        await handleStakeholderInfo(e);
        // await addEventListener_bl_data_log();
        // await addEventListener_bl_data_stakeholder();
    }


    if (e.target.id === "wkRemove") {
        await handleWorkRemove(e);
        await handleWorkInfo(1);
        await addEventListener_bl_data_work();
    }

    if (e.target.id === "wkEdit") {
        await handleVisible(e);
        await addEventListener_fileList(e);
    }

    if (e.target.id === "wkEditSubmit") {
        await handleWorkModify(e);
        await handleWorkFileUpLoad(e);
        await handleWorkInfo(1);
        await addEventListener_bl_data_work();
    }


    if (e.target.id === "wkEditCancel") {
        await handleVisible(e);
    }

    if (e.target.id === "wkRegister") {
        await handleWorkRegister(e);
        await handleWorkInfo(1);
        await addEventListener_bl_data_work();
    }

    if(e.target.id === "wkRankUp") {
        await handleWorkRankUp(e);
        await handleWorkInfo(1);
        await selectWkId(e);
        await addEventListener_bl_data_work();
    }

    if(e.target.id === "wkRankDown") {
        await handleWorkRankDown(e);
        await handleWorkInfo(1);
        await selectWkId(e);
        await addEventListener_bl_data_work();
    }

    if(e.target.id==="wkParent") {
        await handleChangeParent(e);
        await handleWorkInfo(1);
        await selectWkId(e);
        await addEventListener_bl_data_work();
    }

    if(e.target.id === "wkRegisterChild") {
        await handleRegisterChild(e);
        await handleWorkInfo(1);
        await selectWkId(e);
        await addEventListener_bl_data_work();
    }

    if(e.target.id==="wkRemoveParent") {
        await handleRemoveParent(e);
        await handleWorkInfo(1);
        await selectWkId(e);
        await addEventListener_bl_data_work();
    }

    if (e.target.id === "removeFile") {
        await handleFileRemove(e);
        await handleWorkInfo(1);
        await addEventListener_bl_data_work();
        await handleVisible(e);
    }
}

function addEventListener_bl_data_log() {
    const bl_data = document.querySelector("#bl_lg_data");

    bl_data.removeEventListener('click', handle_bl_data_log_Click);

    // 이벤트 등록
    bl_data.addEventListener('click', handle_bl_data_log_Click);
}

async function handle_bl_data_log_Click(e) {
    console.log("click bl_log_data");

    if (e.target.id === "lgRemove") {
        await handleLogRemove(e);
        await handleLogInfo(e);
    }

    if (e.target.id === "lgEdit") {
        await handleVisible(e);
        await addEventListener_fileList(e);
    }

    if (e.target.id === "lgEditSubmit") {
        await handleLogModify(e);
        await handleLogFileUpLoad(e);
        await handleLogInfo(e);
    }

    if (e.target.id === "lgEditCancel") {
        await handleVisible(e);
    }

    if (e.target.id === "lgRegister") {
        await handleLogRegister(e);
        await handleLogInfo(e);
    }

    if (e.target.id === "removeFile") {
        await handleFileRemove(e);
        await handleLogInfo(1);
        await handleVisible(e);
        await addEventListener_bl_data_log();
    }
}
function addEventListener_bl_data_stakeholder() {
    const bl_data = document.querySelector("#bl_sh_data");

    bl_data.removeEventListener('click', handle_bl_data_stakeholder_Click);

    // 이벤트 등록
    bl_data.addEventListener('click', handle_bl_data_stakeholder_Click);
}


async function handle_bl_data_stakeholder_Click(e) {
    console.log("click bl_stakeholder_data");

    if (e.target.id === "shRemove") {
        await handleStakeholderRemove(e);
        await handleStakeholderInfo(e);
        await addEventListener_bl_data_stakeholder();
    }

    if (e.target.id === "shEdit") {
        await handleVisible(e);
    }

    if (e.target.id === "shEditSubmit") {
        await handleStakeholderModify(e);
        await handleStakeholderInfo(e);
        await addEventListener_bl_data_stakeholder();
    }

    if (e.target.id === "shEditCancel") {
        await handleVisible(e);
    }

    if (e.target.id === "shRegister") {
        await handleStakeholderRegister(e);
        await handleStakeholderInfo(e);
        await addEventListener_bl_data_stakeholder();
    }
}