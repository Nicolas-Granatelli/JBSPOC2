package br.com.spring.mvc.mudi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.spring.mvc.mudi.model.Pedido;
import br.com.spring.mvc.mudi.model.StatusPedido;
import br.com.spring.mvc.mudi.repository.PedidoRepository;
import br.com.spring.mvc.mudi.repository.PedidoRepository_JPA;

@Controller
public class HomeController {

	// usando springDATA
	@Autowired
	private PedidoRepository repository;

	// usando JPA


	@GetMapping("/home/{status}")
	public String porStatus(@PathVariable("status") String status, Model model) {
		// usando springDATA
		List<Pedido> pedidos = repository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));

		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status",status);
		return "home";
	}	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
	
	@GetMapping("/home")
	public String home(Model model) {
		// usando springDATA
		List<Pedido> pedidos = repository.findAll();

		model.addAttribute("pedidos", pedidos);
		return "home";
	}
	@GetMapping("/")
	public String inicial(Model model) {
		return "redirect:/home";
	}

	
	
	@GetMapping("/homeFIXO")
	public String homeFIXO(Model model) {

		Pedido pedido = new Pedido();

		pedido.setNomeProduto("Celular fácil de usar Conecta Lite 3G 32GB Wi-Fi Tela 4");
		pedido.setUrlImagem("https://m.media-amazon.com/images/I/51mtoodQ+OL._AC_SX679_.jpg");
		pedido.setUrlProduto(
				"https://www.amazon.com.br/Celular-f%C3%A1cil-Conecta-Wi-Fi-Android/dp/B0CFYSRTNT/ref=sr_1_1_sspa?keywords=celular&qid=1695328519&sr=8-1-spons&ufe=app_do%3Aamzn1.fos.fcd6d665-32ba-4479-9f21-b774e276a678&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1");
		pedido.setDescricao("DADOS FIXOS");
		pedido.setStatus(StatusPedido.AGUARDANDO);

		List<Pedido> pedidos = Arrays.asList(pedido);

		model.addAttribute("pedidos", pedidos);
		return "home";
	}

}
