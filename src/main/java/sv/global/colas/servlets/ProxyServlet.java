package sv.global.colas.servlets;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/proxy/carnet")
public class ProxyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carnet = request.getParameter("carnet");
        if (carnet == null || carnet.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Missing carnet parameter\"}");
            return;
        }

        // Obtener el encabezado Authorization desde la solicitud HTTP
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"Missing or invalid Authorization header\"}");
            return;
        }

        // Usar el encabezado Authorization tal como está, sin volver a codificar
        String apiUrl = "https://api.ues.edu.sv/api/carnet/" + carnet;
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Pasar el mismo encabezado Authorization al servidor externo
        conn.setRequestProperty("Authorization", authHeader);

        try {
            int responseCode = conn.getResponseCode();
            response.setStatus(responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder responseOutput = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseOutput.append(inputLine);
                }
                in.close();

                response.setContentType("application/json");
                response.getWriter().write(responseOutput.toString());
            } else {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String errorLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                errorReader.close();

                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"External API error\", \"details\":\"" + errorResponse.toString() + "\"}");
            }
        } finally {
            conn.disconnect();
        }
    }

}