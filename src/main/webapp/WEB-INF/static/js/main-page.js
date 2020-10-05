var items;

fetch('/api/marketplace/item/all').then(function (response) {
    if (response.ok) {
        response.json().then(function (json) {
            items = json;
            initialize();
            itemCardClickListener();
        });
    } else {
        console.log('Network request for items failed with response ' + response.status + ': ' + response.statusText);
    }
})

function initialize() {
    var itemCardsWrapper = document.getElementsByClassName('item-cards-wrapper')[0];

    var finalGroup = items;
    updateDisplay();

    function updateDisplay() {
        while (itemCardsWrapper.firstChild) {
            itemCardsWrapper.removeChild(itemCardsWrapper.firstChild);
        }

        if (finalGroup.length === 0) {
            var para = document.createElement('p');
            para.textContent = 'Nothing found!';
            itemCardsWrapper.appendChild(para);
        } else {
            for (var i = 0; i < finalGroup.length; i++) {
                showItem(finalGroup[i]);
            }
        }
    }

    function showItem(item) {
        var itemCard = document.createElement('div');
        var itemCardImageWrapper = document.createElement('div');
        var itemCardImage = document.createElement('img');
        var itemCardContentWrapper = document.createElement('div');
        var itemCardContentTitle = document.createElement('h6');
        var itemCardContentSellerDate = document.createElement('div');
        var itemCardContentTextSeller = document.createElement('span');
        var itemCardContentTextDate = document.createElement('span');
        var itemCardContentPriceBids = document.createElement('div');
        var itemCardContentPriceText = document.createElement('span');
        var itemCardContentBidsText = document.createElement('span');

        itemCard.setAttribute('class', 'item-card');
        itemCard.setAttribute('id', item.id);
        itemCardImageWrapper.setAttribute('class', 'item-card-image-wrapper');
        itemCardContentWrapper.setAttribute('class', 'item-card-content-wrapper');
        itemCardContentTitle.setAttribute('class', 'item-card-content-title');
        itemCardContentSellerDate.setAttribute('class', 'item-card-content-seller-date');
        itemCardContentTextSeller.setAttribute('class', 'item-card-content-text');
        itemCardContentTextDate.setAttribute('class', 'item-card-content-text');
        itemCardContentPriceBids.setAttribute('class', 'item-card-content-price-bids');
        itemCardContentPriceText.setAttribute('class', 'item-card-content-price-text');
        itemCardContentBidsText.setAttribute('class', 'item-card-content-text');

        itemCardImage.src = item.pictureLink;
        itemCardContentTitle.textContent = item.name;
        itemCardContentTextSeller.textContent = item.seller;
        itemCardContentTextDate.textContent = convertStopDate(item.stopDate);
        itemCardContentPriceText.textContent = '$' + item.currentPrice;
        itemCardContentBidsText.textContent = item.bidsQty + ' bids';

        itemCard.appendChild(itemCardImageWrapper);
        itemCardImageWrapper.appendChild(itemCardImage);

        itemCard.appendChild(itemCardContentWrapper);
        itemCardContentWrapper.appendChild(itemCardContentTitle);
        itemCardContentWrapper.appendChild(itemCardContentSellerDate);
        itemCardContentSellerDate.appendChild(itemCardContentTextSeller);
        itemCardContentSellerDate.appendChild(itemCardContentTextDate);

        itemCardContentWrapper.appendChild(itemCardContentPriceBids);
        itemCardContentPriceBids.appendChild(itemCardContentPriceText);
        itemCardContentPriceBids.appendChild(itemCardContentBidsText);

        itemCardsWrapper.appendChild(itemCard);
    }
}

function itemCardClickListener() {
    $('.item-card').click(function(e) {
        let id = $(this).attr('id');
        window.location.href = '/marketplace/item/' + id;
    });
}

function convertStopDate(stopDate) {
    let date = new Date(stopDate);
    let today = new Date();
    let result = null;
    let daysDifference = Math.trunc((date.getTime() - today.getTime()) / (1000 * 60 * 60 * 24));
    let minDaysCount = 6;
    if (daysDifference < minDaysCount) {
        if (daysDifference > 1) {
            result = daysDifference + " " + "Days" + " " + "Left";
        } else {
            result = daysDifference + " " + "Day" + " " + "Left";
        }
    } else {
        result = date.toDateString().substr(4, 6);
    }

    return result;
}