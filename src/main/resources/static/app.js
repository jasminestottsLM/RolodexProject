const baseurl = 'http://localhost:14400/cards';

function createListElement(card) {
	$('<li></li>')
		.html(`
			<a href="#" data-card-id="${card.id}">
			${card.last_name}, ${card.first_name}
			</a>
			<form class="delete-card-form" method="post" action="/cards/${card.id}">
				<button>Delete This Crap!</button>
			</form>
		`)
		.appendTo($('#card-list'));
}

$(document).on('submit', '.delete-card-form', function (e) {
	e.preventDefault();
	
	$.ajax(this.action, { type: 'DELETE' })
		.done(() => {
			$(this)
				.closest('li')
				.remove();
		})
		.fail(error => console.error(error));
});

$('#create-card-form').on('submit', function(e) {
	e.preventDefault();
	
	let payload = {
		first_name: $('#first_name').val(),
		last_name: $('#last_name').val(),
		person_title: $('#person_title').val(),
		person_co: $('#person_co').val(),
		picture: $('#picture').val()
	};
	
	let ajaxOptions = {
		type: 'POST',
		data: JSON.stringify(payload),
		contentType: 'application/json'
	};
	
	$.ajax(this.action, ajaxOptions)
		.done(function (card) {
			createListElement(card);
		})
		.fail(error => console.error(error));		
});

$(document).on('submit', '#create-phone-form', function (e) {
	e.preventDefault();
	
	let payload = {
			phone_type: $('#phone_type').val(),
			phone_number: $('#phone_number').val()
		};
		
		let ajaxOptions = {
			type: 'POST',
			data: JSON.stringify(payload),
			contentType: 'application/json'
		};
		
		$.ajax(this.action, ajaxOptions)
			.done(function (card) {
				console.log(card);				
			})
			.fail(error => console.error(error));
});

$(document).on('click', 'a[data-card-id]', function (e) {
	e.preventDefault();
	const cardId = $(this).data('cardId');
	
	$.getJSON(baseurl + '/' + cardId, function (data) {
		data.person_co = data.person_co || '<i>no person_co specified</i>';
		
		let html = `
			<h1>${data.first_name} ${data.last_name}</h1>
			<h2>${data.person_co}</h2>
			<div>Title: ${data.person_title}</div>
			`;
		for (let phone of data.phoneNumbers) {
			html += `
				<div>
					<b>${phone.phone_type}</b>
					<div>${phone.phone_number}</div>
				</div>
			`;
		}
		html += `
				<form id="create-phone-form"method="post" action="/cards/${data.id}/phones">
				<input name="phone_type" id="phone_type" required placeholder="phone_type">
				<br>
				<input name="phone_number" id="phone_number" required placeholder="phone_number">
				<br>
				<button>Add this phone crap!</button>
				</form>
			`;
		$('#card-detail').html(html);
			
	});
});

$.getJSON(baseurl, function (data) {
	if (data.length) {
		for (let card of data) {
			createListElement(card);
		}
	} else {
		$('<li></li>')
			.css('color', 'red')
			.html('You have no data.')
			.appendTo($('#card-list'));
	}
});
