package booklist.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import booklist.bean.Book;

@WebServlet("/BookList")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		DataSource dataSource = null;
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("jdbc/dashDB-sample");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		ArrayList<Book> bookList = new ArrayList<Book>();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn
						.prepareStatement("select \"TITLE\", \"TYPE\", \"PRICE\", \"ISBN\" from \"BOOKLIST\"");
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Book book = new Book(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("bookList", bookList);

		RequestDispatcher rd = request.getRequestDispatcher("./result.jsp");
		rd.forward(request, response);
	}
}