package br.com.luciano.felipe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.luciano.felipe.modelo.Banco;
import br.com.luciano.felipe.modelo.Empresa;

@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Empresa> empresas = new Banco().getEmpresas();
		
		String valor = request.getHeader("accept");
		
		if(valor.contains("xml")) {
			
			XStream xsStream = new XStream();
			xsStream.alias("empresas",Empresa.class);
			String xml = xsStream.toXML(empresas);
			
			
			response.setContentType("application/XML");
			response.getWriter().print(xml);
			
			
		}else {
			
			Gson gson = new Gson();
			String json = gson.toJson(empresas);

			response.setContentType("application/json");
			response.getWriter().print(json);
			
		}
		
		
		


	}

}
