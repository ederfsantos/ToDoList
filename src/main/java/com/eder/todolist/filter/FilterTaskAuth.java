package com.eder.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eder.todolist.user.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var servletPath = request.getServletPath();
		if (servletPath.startsWith("/tasks/")) {
			// pegar a autenticação(usuario e senha)

			var autorizacao = request.getHeader("Authorization");
			System.out.println(autorizacao);
			var user_pass = autorizacao.substring("Basic".length()).trim();
			System.out.println("sem os espaços");
			System.out.println(user_pass);

			byte[] authDecode = Base64.getDecoder().decode(user_pass);
			System.out.println(authDecode);
			var authString = new String(authDecode);
			String[] credencial = authString.split(":");
			String username = credencial[0];
			String password = credencial[1];
			System.out.println("Posição [0] do vetor: " + username);
			System.out.println("Posição [1] do vetor:" + password);

			// pegar a autentcação(usuario e senha)

			var user = this.userRepository.findByUsername(username);
			if (user == null) {
				response.sendError(401, "Usuário sem autorização");
			} else {
				// validar senha
				var verificarSenha = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
				if (verificarSenha.verified) {
					request.setAttribute("idUser",user.getId());//pegando o id do usuario da requisição
					filterChain.doFilter(request, response);
				} else {
					response.sendError(401);
				}
			}

		} else {
			// segue o fluxo
			filterChain.doFilter(request, response);
		}

	}

}
