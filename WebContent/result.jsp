<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="booklist.bean.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book List</title>
</head>

<body>
	<h1>蔵書一覧</h1>

	<table border="1">
		<thead>
			<tr>
				<th>タイトル</th>
				<th>種類</th>
				<th>価格</th>
				<th>ISBN</th>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<Book> bookList = (ArrayList<Book>) request.getAttribute("bookList");
				for (int i = 0; i < bookList.size(); i++) {
					Book book = bookList.get(i);
					out.println("<tr>");
					out.println("<td>" + book.getTitle() + "</td>");
					out.println("<td>" + book.getType() + "</td>");
					out.println("<td>" + book.getPrice() + "</td>");
					out.println("<td>" + book.getIsbn() + "</td>");
					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>