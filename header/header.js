$(document).ready(getDropdown);

let dropdown;

function getDropdown() {
    $.get('/account-dropdown/dropdown.html', function (html_string) {
        dropdown = html_string;
        headerInit();
    });
}

function headerInit() {
    const buttonContainerSelector = '.app-header-buttons-container';
    $(buttonContainerSelector).children().remove();
    if (document.cookie.includes('logged-in=true')) {
        $(buttonContainerSelector).append(dropdown);
    } else {
        $(buttonContainerSelector)
            .append('<button class="app-header-login-button">LOG IN</button>')
            .append('<button class="app-header-join-button">JOIN</button>');
    }

    initHandlers();
}

function initHandlers() {
    console.log('get-handler');

    $('.app-header-login-button').click(() => {
        showModal('.signin-modal-wrapper');
    })

    $('.app-header-join-button').click(() => {
        showModal('.signup-modal-wrapper');
    })

    $('.signin-modal-button').click(() => {
        document.cookie = encodeURIComponent("logged-in") + '=' + encodeURIComponent("true") + ";";
        hideModal('.signin-modal-wrapper');
        headerInit();
    })

    $('.modal-close-button').click(() => {
        hideModal('.signin-modal-wrapper');
        hideModal('.signup-modal-wrapper');
    })

    $('.signin-modal-backdrop').click(() => {
        hideModal('.signin-modal-wrapper');
    })

    $('.signup-modal-backdrop').click(() => {
        hideModal('.signup-modal-wrapper');
    })

    $('.app-header-signout-button').click(() => {
        deleteAllCookies();
        headerInit();
        if ($('div').is('.bids-container') || $('div').is('.items-container')) {
            window.location.href = '/main-page.html';
        }
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
}

function deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/;";
    }
}