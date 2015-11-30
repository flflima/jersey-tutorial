package br.com.restful.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.restful.model.Banda;

@Path("/bandas")
public class BandaResource 
{
	static private Map<Integer, Banda> bandasMap;  
	
	static 
	{ 
		bandasMap = new HashMap<Integer, Banda>();
		Banda b1 = new Banda();
		b1.setId(1);
		b1.setNome("Led Zeppelin");
		b1.setAnoDeFormacao(1968);
		bandasMap.put(b1.getId(), b1);
		Banda b2 = new Banda();
		b2.setId(2); 
		b2.setNome("AC/DC"); 
		b2.setAnoDeFormacao(1973);
		bandasMap.put(b2.getId(), b2); 
	}
	
	@GET
	@Produces("text/xml")
	public List<Banda> getBandas() 
	{ 
		return new ArrayList<Banda>(bandasMap.values());
	}
	
	@Path("{id}")
	@GET
	@Produces("text/xml")
	public Banda getBanda(@PathParam("id") int id) 
	{
		return bandasMap.get(id);
	}
	
	@POST
	@Consumes("text/xml")
	@Produces("text/plain")
	public String adicionaBanda(Banda banda) 
	{
		// para adicionar um objeto:
		// curl -X POST -H "Content-type:text/xml" -d "<banda><nome>Teste</nome><anoDeFormacao>2010</anoDeFormacao></banda>" http://localhost:8080/jersey-tutorial/bandas
		banda.setId(bandasMap.size() + 1);
		bandasMap.put(banda.getId(), banda);
		return banda.getNome() + " adicionado.";
	}

}
