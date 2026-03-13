# Imagen base Nginx para servir archivos estaticos del frontend.
FROM nginx:1.27-alpine

# Copia configuracion personalizada:
# - sirve index.html
# - reenvia /api al backend Spring dentro de la red de Compose.
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copia unicamente los archivos publicos del frontend.
COPY index.html /usr/share/nginx/html/index.html
COPY css /usr/share/nginx/html/css
COPY js /usr/share/nginx/html/js

# Documenta el puerto HTTP interno de Nginx.
EXPOSE 80
