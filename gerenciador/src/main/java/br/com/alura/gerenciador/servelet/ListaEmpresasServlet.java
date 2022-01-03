package br.com.alura.gerenciador.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListaEmpresasSerlvet
 */
@WebServlet("/listaEmpresas")
public class ListaEmpresasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Banco banco = new Banco();
		List<Empresa> lista = banco.getEmpresas();
//		PrintWriter out = response.getWriter();
		
		RequestDispatcher rd = request.getRequestDispatcher("/ListaEmpresa.jsp");
		request.setAttribute("empresas",lista);
		rd.forward(request, response);
		
//		out.println("<html><body>");
//		out.println("<ul>");
//		for (Empresa empresa : lista) {
//			out.println("<li>"+ empresa.getNome()+"</li>");
//		}
//		
//	//Abaixo fiz um for normal como teste
//		
////		for (int i = 0; i < lista.size(); i++) {
////			out.println("<li>"+ lista.get(i).getNome()+"</li>");
////		}
//		
//
//		out.println("</ul>");
//		out.println("</body></html>");
	}
}