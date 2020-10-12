$(document).ready(getDropdown);

let dropdown;

function getDropdown() {
    $.get('/static/components/dropdown.html', function (html_string) {
        dropdown = html_string;
        headerInit();
    });
}

function headerInit() {
    const buttonContainerSelector = '.app-header-buttons-container';
    $(buttonContainerSelector).children().remove();
    fetch('/api/marketplace/authorization', {
        method: 'GET',
    }).then((response) => {
    debugger
        if (response.ok) {
            $(buttonContainerSelector).append(dropdown);
        } else {
            $(buttonContainerSelector)
                .append('<button class="app-header-login-button">LOG IN</button>')
                .append('<button class="app-header-join-button">JOIN</button>');
        }
        initHandlers();
    });
}

function initHandlers() {

    $('.app-header-login-button').click(() => {
        showModal('.signin-modal-wrapper');
    })

    $('.app-header-join-button').click(() => {
        showModal('.signup-modal-wrapper');
    })

    $('.modal-close-button').click(() => {
        hideModal('.signin-modal-wrapper');
        hideModal('.signup-modal-wrapper');
    })

    $('.signin-modal-backdrop').click(() => {
        hideModal('.signin-modal-wrapper');
    })

    $('.signin-modal-create-account-a').click(() => {
        hideModal('.signin-modal-wrapper');
        showModal('.signup-modal-wrapper');
    })

    $('.signup-modal-login-a').click(() => {
        hideModal('.signup-modal-wrapper');
        showModal('.signin-modal-wrapper');
    })

    $('.signup-modal-backdrop').click(() => {
        hideModal('.signup-modal-wrapper');
    })

    $('.app-header-signout-button').click(() => {
        fetch('/api/marketplace/signout', {
            method: 'POST',
        }).then((response) => {
            response.ok && window.location.reload();
        })
        headerInit();
    })
}

function showModal(modalName) {
    $('.app').css('overflow', 'hidden');
    $(modalName)
        .css('display', 'flex')
        .hide()
        .fadeIn();
}

function hideModal(modalName) {
    $('.app').css('overflow', 'auto');
    $(modalName).fadeOut();
    clearAllInputs();
}

function clearAllInputs() {
    var elements = document.getElementsByTagName('input');
    for (var i = 0; i < elements.length; i++) {
        elements[i].value = '';
    }
}

function deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/;domain=localhost;";
    }
}