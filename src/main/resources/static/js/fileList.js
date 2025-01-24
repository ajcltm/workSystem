function addEventListener_fileList(e) {
    const bl_data_item = e.target.closest(".bl_data_item")
    const fileInput = bl_data_item.querySelector('input.bl_data_item_file_edit');
    const fileList = bl_data_item.querySelector('.fileList');

    fileInput.addEventListener('change', () => {
        // 기존 목록 초기화
        fileList.innerHTML = '';

        // 첨부된 파일 목록 가져오기
        const files = fileInput.files;

        // 파일 목록을 표시
        Array.from(files).forEach((file) => {
            const listItem = document.createElement('span');

            // 파일 이름 제한: 10글자 + '...'
            const truncatedName = file.name.length > 10
                ? file.name.slice(0, 10) + '...'
                : file.name;

            listItem.textContent = `${truncatedName} (${(file.size / 1024).toFixed(2)} KB)  `;
            fileList.appendChild(listItem);
        });
    });
}
