$('.add-item-button').click(() => {
    $('.app').css('overflow', 'hidden');
        $('.item-info-modal-wrapper')
            .css('display', 'flex')
            .hide()
            .fadeIn();
})

$('.item-info-modal-close-button').click(() => {
    $('.app').css('overflow', 'auto');
    $('.item-info-modal-wrapper').fadeOut();
})

$('.item-info-modal-backdrop').click(() => {
    $('.app').css('overflow', 'auto');
    $('.item-info-modal-wrapper').fadeOut();
})