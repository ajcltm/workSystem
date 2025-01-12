document.addEventListener("DOMContentLoaded", async () => {
    console.log("HTML 문서가 로드되었습니다!");
    await handleWorkInfo(1);
    await addEventListener_bl_data_work()
});

function addEventListener_bl_data_work() {
    const bl_data = document.querySelector(".bl_data");
    bl_data.addEventListener('click', async function (e) {
        console.log("click bl_data");

        const bl_data_item_box = e.target.closest('.bl_data_item_box');
        const bl_data_item_subBox = e.target.closest('.bl_data_item_subBox');

        if (bl_data_item_box) {
            console.log("click bl_data_item_box")
            await handleLogInfo(e);
            await handleStakeholderInfo(e);
        }

        if (bl_data_item_subBox) {
            console.log("click bl_data_item_box")
            await handleLogInfo(e);
            await handleStakeholderInfo(e);
        }

        if (e.target.id === "wkRemove") {
            await handleWorkRemove(e);
            await handleWorkInfo(1);
            await addEventListener_bl_data_work();
        }

        if (e.target.id === "wkEdit") {
            await handleVisible(e);
        }

        if (e.target.id === "wkEditSubmit") {
            await handleWorkModify(e);
            await handleFileUpLoad(e);
            await handleWorkInfo(1);
            await addEventListener_bl_data_work();
        }


        if (e.target.id === "wkEditCancel") {
            await handleVisible(e);
        }
    })
}