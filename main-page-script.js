$('.app-header-login-button').click(() => {
    $('.app').css('overflow', 'hidden');
    $('.signin-modal-wrapper')
    .css('display', 'flex')
    .hide()
    .fadeIn();
})

$('.app-header-join-button').click(() => {
    $('.app').css('overflow', 'hidden');
    $('.signup-modal-wrapper')
    .css('display', 'flex')
    .hide()
    .fadeIn();
})

$('.modal-close-button').click(() => {
    $('.app').css('overflow', 'auto');
    $('.signup-modal-wrapper').fadeOut();
    $('.signin-modal-wrapper').fadeOut();
})