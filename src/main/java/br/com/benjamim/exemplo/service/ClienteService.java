package br.com.benjamim.exemplo.service;

import br.com.benjamim.exemplo.bean.Cliente;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ClienteService {

    private static final String URL = "http://localhost:8082/exemplo-jersey-servico";
    private static final String RESOURCE = "/api/cliente/";

    public static void main(String[] args) {

        Long id = 1l;

        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget webTarget = client.target(URL);

        // RETORNA UM CLIENTE PELO ID
        Cliente retornoCliente = webTarget.path(RESOURCE + id).request().get(Cliente.class);
        System.out.println(retornoCliente.getCpf());

        // ADICIONANDO UM NOVO CLIENTE

        Cliente novoCliente = new Cliente();
        novoCliente.setCpf("16766350091");
        novoCliente.setNome("Valxa Xadeuba Kilha");
        novoCliente.setEndereco("Estrada Principal, s/n");

        Response response = webTarget.path(RESOURCE).request().post(Entity.entity(novoCliente, MediaType.APPLICATION_JSON_TYPE));

        System.out.println("Status: " + response.getStatus());
        System.out.println("Mensagem: " + response.readEntity(String.class));

        // RETORNA UMA LISTA DE CLIENTES
        List<Cliente> clientes = webTarget.path(RESOURCE).request().get(new GenericType<List<Cliente>>(){});
        System.out.println(clientes);

    }

}
