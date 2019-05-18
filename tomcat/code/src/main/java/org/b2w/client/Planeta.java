package org.b2w.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.json.JSONException;
import org.json.JSONObject;

//import org.springframework.data.cassandra.core.mapping.Column;

//@Table
@Entity
public class Planeta {

	public Planeta() {
	}

	public Planeta(String nome, String clima, String terreno) {
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
	}

	// @PrimaryKey

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// @Column
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// @Column
	private String clima;

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	// @Column
	private String terreno;

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	@Transient
	private int filmesRelacionados;

	public int getFilmesRelacionados() {
		URL acesso;
		try {
			String endereco = "https://swapi.co/api/planets/?format=json&search=" + nome;
			acesso = new URL(endereco);
			HttpsURLConnection conexao = (HttpsURLConnection) acesso.openConnection();
			conexao.setRequestMethod("GET");
			conexao.setRequestProperty("User-Agent", "Mozilla/5.0");
			conexao.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject planetStarWar = new JSONObject(response.toString());
			return planetStarWar.getJSONArray("results").getJSONObject(0).getJSONArray("films").length();

		} catch (MalformedURLException e) {
			return 0;
		} catch (IOException e) {
			return 0;
		} catch (JSONException e) {
			return 0;
		}
	}
}
