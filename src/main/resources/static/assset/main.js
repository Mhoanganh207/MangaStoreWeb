

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
						+ '<button style="height: 17px; z-index:1"; onclick="removeCart(' + length + ')"> Delete</button>' +
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

      var length = Object.keys(list).length;
      
      
      if(length===0){
		  document.getElementById("cart").classList.add("cartlist-empty");
	  }
	  else{
		  
	    document.getElementById("cart").classList.remove("cartlist-empty");
          
 
			for (let i = 0; i < length; i++) {
				var bookurl = 'http://localhost:8080/book/' + list[i];
				if (list[i] !== -1) {
					$.ajax({
						type: "Get",
						url: bookurl,
						success: function(data) {
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
								+ '<button style="height: 17px;z-index:1"; onclick="removeCart(' + i + ')"> Delete</button>' +
								'</div>';

							$("#list-item").prepend(addhtml);
							

						}
					})
				}

			}
			}
		}
	})

}

function removeCart(id) {
	id = '' + id;
	var parent = document.getElementById("list-item");
	var child = document.getElementById(id);
	parent.removeChild(child);
	var bookurl = "http://localhost:8080/book/" + id;
	$.ajax({
		type: "delete",
		url: bookurl,
		success: function() {
			console.log("delete successed");
		}
	})
	setTimeout(
		function() {
			$.ajax({
				type: "Get",
				url: "http://localhost:8080/booklist",
				success: function(list) {
					if (Object.keys(list).length === 0) {
						document.getElementById("cart").classList.add("cartlist-empty");
					}
				}
			})
		}, 40

	)

}


function AddCheckOutCart() {

	$.ajax({
		type: "Get",
		url: "http://localhost:8080/booklist",
		success: function(list) {
			
	if(Object.keys(list).length !==0){
			for (let i = 0; i < Object.keys(list).length; i++) {
				var bookurl = 'http://localhost:8080/book/' + list[i];
				if (list[i] !== -1) {
					$.ajax({
						type: "Get",
						url: bookurl,
						success: function(data) {
							var addhtml = '';
							addhtml +=
								'<div id="' + i + '">' +
								'<table class="order-table">' +
								'<tbody>' +

								'<tr>' +
								'<td><img src="' + data.imageURL + '" class="full-width"></img>' +
								'</td>' +
								'<td>' +
								'<button class="delete-btn" onclick="removeCheckOutCart(' + i + ')"> Delete</button>' +
								'<br> <div class="thin" style="width : 230px">' + data.name + '</div>' +
								'<br>' + data.category + '<br> <span class="thin small"> By' + data.author + '<br><br></span>' +
								'</td>' +

								' </tr>' +

								'<td>' +
								'<div class="price">$' + data.price + '</div>' +
								'</td>' +
								'</tr>' +
								'</tbody>' +

								'</table>' +
								'<div class="line"></div>'
								+ '</div>';

							$("#checkoutCart").prepend(addhtml);
							

						}
					})
				}

			}
		}
		}
	})

}
function removeCheckOutCart(id) {
	id = '' + id;
	var bookurl = "http://localhost:8080/book/" + id;
	$.ajax({
		type: "delete",
		url: bookurl,
		success: function() {
			console.log("delete successed");
		}
	})
	var parent = document.getElementById("checkoutCart");
	var child = document.getElementById(id);
	parent.removeChild(child);



}
