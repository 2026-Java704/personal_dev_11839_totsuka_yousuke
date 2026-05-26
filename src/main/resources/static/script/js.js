 //行追加
 function add(){
	const newRow = tbl.rows[1].cloneNode(true);

		// 入力値を空にする
		let inputs = newRow.getElementsByTagName("input");
		for (let i = 0; i < inputs.length; i++) {
			inputs[i].value = "";
		}

		// セレクトボックスを先頭に戻す
		let selects = newRow.getElementsByTagName("select");
		for (let i = 0; i < selects.length; i++) {
			selects[i].selectedIndex = 0;
		}

		tbl.appendChild(newRow);
 }

 //末尾行削除
 function del(){
   let rw = tbl.rows.length;
   tbl.deleteRow(rw-1);
 }//