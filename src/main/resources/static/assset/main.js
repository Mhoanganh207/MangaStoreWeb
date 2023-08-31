function PostId(id) {

	setTimeout(function() {
		var bookid = id
		var SendInfo = { "id": bookid }
		$.ajax({
			type: 'post',
			url: 'http://localhost:8080/book/id',
			data: JSON.stringify(SendInfo),
			contentType: "application/json; charset=utf-8",
			traditional: true,
			success: function() {
				console.log("post successed")
			}
		})
	}, 50)
};



function AddCart(event, id) {


	var bookurl = 'http://localhost:8080/book/';
	bookurl = bookurl + id


	$.ajax({
		type: "Get",
		url: "http://localhost:8080/booklist",
		success: function(list) {
			var length = Object.keys(list).length;

			$.ajax({
				type: "Get",
				url: bookurl,
				success: function(data) {
					document.getElementById("cart").classList.remove("cartlist-empty");
					var addhtml = '';
					addhtml +=
						'<div class="cartlist-item" id="' + length + '">' +
						'<div>' +
						'<img class="cartlist-item-image" src="' + data.imageURL + '">' +
						'</div>' +

						'<div class="item-info">' +
						'<h2 class="item-name">' + data.name + '</h2>' +
						'<h3>' + data.author + '</h3>' +
						'<h2 style="color:#3f9acd">$' + data.price + ' </h2>' +
						'</div>' 
						+ '<button style="height: 17px; z-index:1;"> Delete</button>'+
						'</div>';

					$("#list-item").prepend(addhtml);

				}
			})
		}
	})





};

function AddCartList() {

	$.ajax({
		type: "Get",
		url: "http://localhost:8080/booklist",
		success: function(list) {
			


			for (let i = 0; i < Object.keys(list).length; i++) {
				var bookurl = 'http://localhost:8080/book/' +list[i];
				
				$.ajax({
					type: "Get",
					url: bookurl,
					success: function(data) {
						document.getElementById("cart").classList.remove("cartlist-empty");
						var addhtml = '';
						addhtml +=
							'<div class="cartlist-item" id="' + i + '">' +
							'<div>' +
							'<img class="cartlist-item-image" src="' + data.imageURL + '">' +
							'</div>' +

							'<div class="item-info">' +
							'<h2 class="item-name">' + data.name + '</h2>' +
							'<h3>' + data.author + '</h3>' +
							'<h2 style="color:#3f9acd">$' + data.price + ' </h2>' +
							'</div>' 
							+ '<button style="height: 17px;z-index:1;"> Delete</button>'+
							'</div>';

						$("#list-item").prepend(addhtml);

					}
				})

			}
		}
	})

}
