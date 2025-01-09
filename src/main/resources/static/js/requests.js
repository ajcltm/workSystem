async function sendRequest(url_key, options = {}) {
    const {
        method = 'GET',
        headers = {},
        body = null,
        params = {}, // 쿼리 파라미터
        pathVariables = {}, // PathVariable
    } = options;

    const url = "http://localhost:8080" + url_key;

    // PathVariable 대체
    const interpolatedUrl = url.replace(
        /{([^}]+)}/g,
        (_, key) => pathVariables[key] || `{${key}}`
    );

    // 쿼리 파라미터 추가
    const queryString = new URLSearchParams(params).toString();
    const requestUrl = queryString ? `${interpolatedUrl}?${queryString}` : interpolatedUrl;

    // Fetch 호출
    const response = await fetch(requestUrl, {
        method,
        headers,
        body,
    });

    // 응답 확인 및 처리
    if (!response.ok) {
        const error = await response.text();
        throw new Error(`Error: ${response.status} - ${error}`);
    }

    return response;
}

// ====================== workInfo =======================

async function workInfo() {

    const url = "/workSystem/workInfo/data"
    const response = await sendRequest(url)
    return response;
}

// ====================== work =======================

async function workRegister(formData) {
    const url = "/workSystem/work/register"
    const option = {
        method: 'POST',
        body: formData,
    };
    const response = await sendRequest(url, option)
    return response;
}

async function workRemove(id) {
    const url = "/workSystem/work/remove"
    const option = {
        params: {wkId : id},
    };
    const response = await sendRequest(url, option)
    return response;
}

async function workModify(formData) {
    const url = "/workSystem/work/modify"
    const option = {
        method: 'POST',
        body: formData,
    };
    const response = await sendRequest(url, option)
    return response;
}

async function workRankUp(id) {
    const url = "/workSystem/work/rankUp"
    const option = {
        params: {wkId : id},
    };
    const response = await sendRequest(url, option)
    return response;
}

async function workRankDown(id) {
    const url = "/workSystem/work/rankDown"
    const option = {
        params: {wkId : id},
    };
    const response = await sendRequest(url, option)
    return response;
}

async function workChangeParent(parent, id) {
    const url = "/workSystem/work/changeParent"
    const option = {
        params: {
            parent : parent,
            wkId : id
        },
    };
    const response = await sendRequest(url, option)
    return response;
}

// ====================== logInfo =======================

async function logInfo(id) {
    const url = "/workSystem/logInfo/data"
    const option = {
        params: {wkId : id},
    };
    const response = await sendRequest(url, option)
    return response;
}

// ====================== log =======================

async function logRegister(formData) {
    const url = "/workSystem/log/register"
    const option = {
        method: 'POST',
        body: formData,
    };
    const response = await sendRequest(url, option)
    return response;
}

async function logRemove(id) {
    const url = "/workSystem/log/remove"
    const option = {
        params: {lgId : id},
    };
    const response = await sendRequest(url, option)
    return response;
}

async function logModify(formData) {
    const url = "/workSystem/log/modify"
    const option = {
        method: 'POST',
        body: formData,
    };
    const response = await sendRequest(url, option)
    return response;
}

// ====================== stakeholder =======================

async function stakeholderRegister(formData) {
    const url = "/workSystem/stakeholder/register"
    const option = {
        method: 'POST',
        body: formData,
    };
    const response = await sendRequest(url, option)
    return response;
}

async function stakeholderRemove(id) {
    const url = "/workSystem/stakeholder/remove"
    const option = {
        params: {shId : id},
    };
    const response = await sendRequest(url, option)
    return response;
}

async function stakeholderModify(formData) {
    const url = "/workSystem/stakeholder/modify"
    const option = {
        method: 'POST',
        body: formData,
    };
    const response = await sendRequest(url, option)
    return response;
}


// ====================== file =======================

async function fileUpload(formData) {
    const url = "/workSystem/files/upload"
    const option = {
        method: 'POST',
        body: formData,
    };
    const response = await sendRequest(url, option)
    return response;
}


async function fileDownload(id) {
    const url = "/workSystem/files/download"
    const option = {
        params: {wfId : id},
    };
    const response = await sendRequest(url, option)
    return response;
}

async function fileReomve(id) {
    const url = "/workSystem/files/remove"
    const option = {
        params: {wfId : id},
    };
    const response = await sendRequest(url, option)
    return response;
}