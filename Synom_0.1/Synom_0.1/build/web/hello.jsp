<!--<!DOCTYPE html>
<html>
    <head><title>Sample JSP Page</title></head>
    <body>
        <h1>Sample JSP Page</h1>
<%@ page import="java.util.*" %>
<p>
<table>
    <tr>
        <td><%=request.getAttribute("cpuLoad")%></td>
        <td><%=request.getAttribute("memory")%></td>
    </tr>
</table>
</p>
</body></html>-->


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Monitorizare</title>
        <style type="text/css">

            body {
                margin: 0;
                padding: 0;
                overflow: hidden;
                height: 100%; 
                max-height: 100%; 
                font-family:Sans-serif;
                line-height: 1.5em;
            }

            main {
                position: fixed;
                left: 230px; /* Width of the nav bar */
                bottom: 100px; /* Set this to the height of the footer */
                right: 0;
                top: 0;
                bottom: 0;
                overflow: auto; 
                background: #fff;
            }

            #nav {
                position: absolute; 
                left: 0;
                top: 0;
                bottom: 100px; 
                width: 230px;
                overflow: auto; /* Scrollbars will appear on this frame only when there's enough content to require scrolling. To disable scrollbars, change to "hidden", or use "scroll" to enable permanent scrollbars */
                background: #DAE9BC; 		
            }

            #footer {
                position: absolute;
                left: 0;
                bottom: 0;
                width: 100%;
                height: 100px; 
                overflow: hidden; /* Disables scrollbars on the footer frame. To enable scrollbars, change "hidden" to "scroll" */
                background: #BCCE98;
            }

            #logo {
                padding:10px;
            }

            .innertube {
                margin: 15px; /* Provides padding for the content */
            }

            p {
                color: #555;
            }

            nav ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
            }

            nav ul a {
                color: darkgreen;
                text-decoration: none;
            }

            /*IE6 fix*/
            * html body{
                padding: 0 0 100px 230px; /* Set the third value to the height of the footer and last value to the width of the nav */
            }

            * html main{ 
                height: 100%; 
                width: 100%; 
            }

            table, th, td {
                border: 1px solid black;
            }
        </style>		

    </head>

    <body>

        <main>
            <div class="innertube">

                <h1>Resurse</h1>
                <h2>Time on server: <%= new Date()%></h2>
                <p> <table>
                    <tr>
                        <th><%=request.getAttribute("operatingSystem")%></th>
                    </tr>
                    <tr>  
                        <th><%=request.getAttribute("cpuLoad")%></th>
                    </tr>
                    <tr>  
                        <th><%=request.getAttribute("totalRAM")%></th>
                    </tr>  
                    <tr>  
                        <th><%=request.getAttribute("memory")%></th>
                    </tr>  

                </table>
                </p>

            </div>
        </main>

        <nav id="nav">
            <div class="innertube">
                <h2><span>Proiect 3</span></h2>
                        Liviu Rujan<br>
                        Grupa 443A<br>
                        Profesor coordonator S.L. Dr. Ing. C. Stoica
	
            </div>
        </nav>	

        <footer id="footer">
            <div id="logo">
                <h1>Synom</h1>
                <h1></h1>
            </div>
        </footer>

    </body>
</html>