package manager ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Props")
public class PropsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PropsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hours = PropsHelper.get("hours");
		PropsHelper.set("hours", "09-00,19-00");
		
		String days = PropsHelper.get("days");
		PropsHelper.set("days", "Sunday,Monday, Tuesday, Wednesday, Thursday,Friday,Saturday");
	}

}
