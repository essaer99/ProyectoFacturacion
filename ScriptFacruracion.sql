CREATE DATABASE facturacion;
USE facturacion;

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(300) NOT NULL,
    apellido_paterno VARCHAR(300) NOT NULL,
    apellido_materno VARCHAR(300) NOT NULL,
    email VARCHAR(300) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    contrasena VARCHAR(300) NOT NULL
);

CREATE TABLE sesion_usuario (
    id_sesion INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    token_jwt TEXT NOT NULL,
    fecha_creacion DATE,
    fecha_expiracion DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) 
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ticket (
    id_ticket INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    imagen_ruta VARCHAR(900) NOT NULL,
    fecha DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE direccion_completa (
    id_direccionc INT PRIMARY KEY AUTO_INCREMENT,
    estado ENUM('Aguascalientes', 'Baja California', 'Baja California Sur', 'Campeche', 'Chiapas', 
                'Chihuahua', 'Coahuila', 'Colima', 'Durango', 'Guanajuato', 'Guerrero', 'Hidalgo', 
                'Jalisco', 'México', 'Michoacán', 'Morelos', 'Nayarit', 'Nuevo León', 'Oaxaca', 
                'Puebla', 'Querétaro', 'Quintana Roo', 'San Luis Potosí', 'Sinaloa', 'Sonora', 
                'Tabasco', 'Tamaulipas', 'Tlaxcala', 'Veracruz', 'Yucatán', 'Zacatecas', 
                'Ciudad de México', 'Estado de México') NOT NULL,
    municipio VARCHAR(800) NOT NULL,
    cp CHAR(5) NOT NULL,
    calle VARCHAR(800) NOT NULL,
    numero INT NOT NULL
);

CREATE TABLE datos_fiscales (
    id_datos_fiscales INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_direccionc INT NOT NULL,
    rfc CHAR(16) NOT NULL,
    razon_social VARCHAR(800) NOT NULL,
    tipo_contribuyente ENUM('Persona Fisica', 'Persona Moral') NOT NULL,
    regimen_fiscal ENUM('General de Ley Personas Morales', 'Personas Morales con Fines no Lucrativos', 
                        'Régimen de Enajenación o Adquisición de Bienes', 'Residentes en el Extranjero sin Establecimiento Permanente en México',
                        'Actividades Agrícolas, Ganaderas, Silvícolas y Pesqueras', 
                        'Régimen Simplificado de Confianza', 'Hidrocarburos', 'Sueldos y Salarios', 
                        'Arrendamiento', 'Ingresos por intereses', 'Incorporación Fiscal') NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) 
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_direccionc) REFERENCES direccion_completa(id_direccionc)
);

CREATE TABLE factura (
    id_factura INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_ticket INT NOT NULL,
    id_datos_fiscales INT NOT NULL,
    uso_factura ENUM('Adquisición de mercancías', 'Gastos en general', 'Construcciones', 'Equipo de transporte',
                     'Honorarios médicos', 'Gastos funerales', 'Intereses hipotecarios', 'Donativos', 
                     'Pagos por servicios educativos', 'Sin efectos fiscales', 'Nómina', 'Por definir') NOT NULL,
    fecha_emision DATE,
    monto_total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_ticket) REFERENCES ticket(id_ticket),
    FOREIGN KEY (id_datos_fiscales) REFERENCES datos_fiscales(id_datos_fiscales)
);

CREATE TABLE factura_ruta (
    id_factura_ruta INT PRIMARY KEY AUTO_INCREMENT,
    id_factura INT NOT NULL,
    formato LONGBLOB NOT NULL,
    factura_ruta VARCHAR(900) NOT NULL,
    fecha DATE,
    FOREIGN KEY (id_factura) REFERENCES factura(id_factura)
);

CREATE TABLE producto (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(900) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(900) NOT NULL
);

CREATE TABLE productos_ticket (
    id_producto_ticket INT PRIMARY KEY AUTO_INCREMENT,
    id_ticket INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad DECIMAL(10,2) NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_ticket) REFERENCES ticket(id_ticket),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    UNIQUE (id_ticket, id_producto)
);