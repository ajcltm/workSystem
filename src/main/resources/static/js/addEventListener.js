document.addEventListener("DOMContentLoaded", async () => {
    console.log("HTML 문서가 로드되었습니다!");
    await handleWorkInfo(1);

    const bl_data = document.querySelector(".bl_data");
    bl_data.addEventListener('click', async function (e) {
        console.log("click bl_data");

        // 클릭한 요소에서 가장 가까운 li 태그를 찾음
        const wkId = e.target.closest(".bl_data_item").dataset.wkId;

        // li 태그가 존재하고, data-wkId 속성이 있을 경우
        if (wkId) {
            console.log(`wkId: ${wkId}`);
            await handleLogInfo(wkId);
            await handleStakeholderInfo(wkId);
        }
    })

});