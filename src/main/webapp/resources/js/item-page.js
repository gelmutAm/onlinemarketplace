var item;
var path = window.location.href.split("/");
var id = path[path.length - 1];
var contentPadding = document.getElementsByClassName('content-padding')[0];

fetch('/api/marketplace/item/' + id).then(function (response) {
    if (response.ok) {
        response.json().then(function (json) {
            item = json;
            initialize();
            itemBiddingContentBidButtonClick();
        });
    } else {
        const contentPaddingSelector = '.content-padding';
        $(contentPaddingSelector)
            .append('<h1 class=not-found-header>404</h1>')
            .append('<p class="not-found-p">Sorry! We can\'t find what you\'re looking for.</p>');
        console.log('Network request for item failed with response ' + response.status + ': ' + response.statusText);
    }
})

function initialize() {
    updateDisplay();

    function updateDisplay() {
        while (contentPadding.firstChild) {
            contentPadding.removeChild(contentPadding.firstChild);
        }

        showItem(item);
    }

    function showItem(item) {
        var itemContent = document.createElement('div');
        var itemContentImageWrapper = document.createElement('div');
        var itemContentImage = document.createElement('img');
        var itemBiddingContainer = document.createElement('div');
        var itemBiddingTitle = document.createElement('span');
        var itemStopDate = document.createElement('div');
        var itemStopDateTextTimeLeft = document.createElement('span');
        var itemStopDateTextActualTime = document.createElement('span');
        var itemBiddingContent = document.createElement('div');
        var itemBiddingContentPriceBidsContainer = document.createElement('div');
        var itemBiddingContentPrice = document.createElement('span');
        var itemBiddingContentBids = document.createElement('span');
        var biddingForm = document.createElement('form');
        var itemBiddingContentInputButtonContainer = document.createElement('div');
        var itemBiddingInputContainer = document.createElement('div');
        var itemBiddingContentInputButtonContainerLabel = document.createElement('label');
        var itemBiddingContentInputButtonContainerInput = document.createElement('input');
        var itemBiddingContentBidButton = document.createElement('button');
        var itemDescriptionContainer = document.createElement('div');
        var itemDescription = document.createElement('div');
        var itemDescriptionHeader = document.createElement('span');
        var itemDescriptionP = document.createElement('p');

        itemContent.setAttribute('class', 'item-content');
        itemContentImageWrapper.setAttribute('class', 'item-content-image');
        itemBiddingContainer.setAttribute('class', 'item-bidding-container');
        itemBiddingTitle.setAttribute('class', 'item-bidding-title');
        itemStopDate.setAttribute('class', 'item-stop-date');
        itemStopDateTextTimeLeft.setAttribute('class', 'item-stop-date-text');
        itemStopDateTextActualTime.setAttribute('class', 'item-stop-date-text');
        itemBiddingContent.setAttribute('class', 'item-bidding-content');
        itemBiddingContentPriceBidsContainer.setAttribute('class', 'item-bidding-content-price-bids-container');
        itemBiddingContentPrice.setAttribute('class', 'item-bidding-content-price');
        itemBiddingContentBids.setAttribute('class', 'item-bidding-content-bids');
        biddingForm.setAttribute('class', 'bidding-form');
        itemBiddingContentInputButtonContainer.setAttribute('class', 'item-bidding-content-input-button-container');
        itemBiddingInputContainer.setAttribute('class', 'item-bidding-input-container');
        itemBiddingContentInputButtonContainerLabel.setAttribute('for', 'bid');
        itemBiddingContentInputButtonContainerInput.setAttribute('type', 'text');
        itemBiddingContentInputButtonContainerInput.setAttribute('id', 'bid');
        itemBiddingContentInputButtonContainerInput.setAttribute('onkeypress', 'resetValidity()')
        itemBiddingContentBidButton.setAttribute('class', 'item-bidding-content-bid-button');
        itemBiddingContentBidButton.setAttribute('type', 'submit');
        itemDescriptionContainer.setAttribute('class', 'item-description-container');
        itemDescription.setAttribute('class', 'item-description');
        itemDescriptionHeader.setAttribute('class', 'item-description-header');
        itemDescriptionP.setAttribute('class', 'item-description-p');

        itemContentImage.src = item.pictureLink;
        itemBiddingTitle.textContent = item.name;
        itemStopDateTextTimeLeft.textContent = 'TIME LEFT';
        itemStopDateTextActualTime.textContent = item.stopDate;
        itemBiddingContentPrice.textContent = '$' + item.currentPrice;
        itemBiddingContentBids.textContent = item.bidsQty + ' bids';
        itemBiddingContentInputButtonContainerLabel.textContent = 'your maximum bid';
        itemBiddingContentBidButton.textContent = 'place bid';
        itemDescriptionHeader.textContent = 'description';
        itemDescriptionP.textContent = item.description;

        itemContentImageWrapper.appendChild(itemContentImage);
        itemBiddingContainer.appendChild(itemBiddingTitle);
        itemStopDate.appendChild(itemStopDateTextTimeLeft);
        itemStopDate.appendChild(itemStopDateTextActualTime);
        itemBiddingContainer.appendChild(itemStopDate);
        itemBiddingContentPriceBidsContainer.appendChild(itemBiddingContentPrice);
        itemBiddingContentPriceBidsContainer.appendChild(itemBiddingContentBids);
        itemBiddingInputContainer.appendChild(itemBiddingContentInputButtonContainerLabel);
        itemBiddingInputContainer.appendChild(itemBiddingContentInputButtonContainerInput);
        itemBiddingContent.appendChild(itemBiddingContentPriceBidsContainer);
        biddingForm.appendChild(itemBiddingInputContainer);
        biddingForm.appendChild(itemBiddingContentBidButton);
        itemBiddingContentInputButtonContainer.appendChild(biddingForm);
        itemBiddingContent.appendChild(itemBiddingContentPriceBidsContainer);
        itemBiddingContent.appendChild(itemBiddingContentInputButtonContainer);
        itemBiddingContainer.appendChild(itemBiddingContent);

        itemDescription.appendChild(itemDescriptionHeader);
        itemDescription.appendChild(itemDescriptionP);
        itemDescriptionContainer.appendChild(itemDescription);

        itemContent.appendChild(itemContentImageWrapper);
        itemContent.appendChild(itemBiddingContainer);
        contentPadding.appendChild(itemContent);
        contentPadding.appendChild(itemDescriptionContainer);
    }
}

function itemBiddingContentBidButtonClick() {
    const PRICE_REGEX = /^[1-9]{1}[0-9 ]{0,10}$/;

    $('.bidding-form').submit(e => {
        e.preventDefault();

        const ERROR_MESSAGE = "Incorrect price.";

        const biddingInput = e.target[0];

        const isPriceValid = PRICE_REGEX.test(biddingInput.value);

        fetch('/api/marketplace/authorization', {
            method: 'POST'
        }).then((response) => {
            if (response.ok) {
                if (isPriceValid && biddingInput.value > item.currentPrice) {
                    let bid = {
                        itemId: id,
                        price: biddingInput.value
                    };

                    fetch('/api/marketplace/user/bid', {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(bid)
                    }).then((response) => {
                        if (response.ok) {
                        }
                    });
                } else {
                    biddingInput.value = '';
                    biddingInput.setCustomValidity(ERROR_MESSAGE);
                    e.target.reportValidity();
                }
            } else {
                showModal('.signin-modal-wrapper');
            }

            biddingInput.value = '';
        });
    })
}

function resetValidity() {
    $('#bid')[0].setCustomValidity('');
}