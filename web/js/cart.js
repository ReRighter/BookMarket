function buy(bookId){

    $.post("addToCart",{book_id:bookId},function (data){
        if(data ==="ok"){
            alert("成功添加到购物车!")
            location.reload();
        }
        else{
            alert("库存不足!")
        }
    });
}

function lessen(id){
    $.post("bookLessen",{book_id:id},function (data){
        if(data==="ok"){
            alert("成功减少该书数量!")
            location.reload();
        }
    });
}
function deletes(id){
    $.post("bookDelete",{book_id: id},function (data){
        if(data ==="ok"){
            alert("删除成功!");
            location.reload();
        }
    })
}