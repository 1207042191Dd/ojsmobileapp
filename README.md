# OJS Mobile App

## 1. Diseño de la Aplicación

### Pantalla Principal - Listado de Revistas
- Vista inicial con catálogo de revistas científicas
- Interfaz limpia y navegable
![image](https://github.com/user-attachments/assets/8ebb3402-63ef-46bd-9acb-302abf60f9aa)


### Listado de Volúmenes por Revista
- Muestra volúmenes específicos de cada revista
- Diseño intuitivo de navegación

  ![image](https://github.com/user-attachments/assets/fe9078ad-8e54-4b25-aed7-e449ee0fbc61)


### Listado de Artículos por Volumen
- Vista detallada de artículos
- Opciones de descarga directa

  ![image](https://github.com/user-attachments/assets/65a1f7d7-8642-42dc-9e26-ecfbb542a5a6)

![image](https://github.com/user-attachments/assets/8cd0811a-70b1-427d-914e-896f70bb4301)
![image](https://github.com/user-attachments/assets/efe236cd-b4c5-4997-ba50-78586940cca5)


## 2. Funcionamiento

### a) Obtención de Revistas Científicas
La aplicación recupera el listado de revistas científicas de la UTEQ mediante la API:
`https://revistas.uteq.edu.ec/ws/journals.php`

### b) Navegación por Volúmenes
- Evento `click` en revista abre volúmenes publicados
- Datos obtenidos de: `https://revistas.uteq.edu.ec/ws/issues.php?j_id={journal_id}`

### c) Listado de Artículos
- Selección de volumen muestra artículos publicados
- Información recuperada de: `https://revistas.uteq.edu.ec/ws/pubs.php?i_id={issue_id}`

### d) Visualización de Artículos
Dos opciones de visualización por artículo:
- **PDF:** Descarga directa del archivo
- **HTML:** Redirección al enlace DOI en navegador

## 3. Video de Demostración

`https://drive.google.com/file/d/1PsAmdVA4yL9Ew_LEx3SksoOCrvmLKBv4/view?usp=sharing`

## 4. Instalación y Configuración

### Clonar Repositorio
```sh
git clone [https://github.com/tu_usuario/ojs-mobile-app.git](https://github.com/1207042191Dd/ojsmobileapp/)
```

### Dependencias
Agrega en `build.gradle (Module: app)`:
```gradle
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
}
```

### Permisos de Aplicación
En `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```


## 5. APIs Utilizadas
- **Revistas:** `https://revistas.uteq.edu.ec/ws/journals.php`
- **Volúmenes:** `https://revistas.uteq.edu.ec/ws/issues.php?j_id={journal_id}`
- **Artículos:** `https://revistas.uteq.edu.ec/ws/pubs.php?i_id={issue_id}`

## 6. Contribución
Mejoras son bienvenidas:
1. Haz un fork del repositorio
2. Crea una rama para tu feature
3. Envía un Pull Request

## 7. Autor
Desarrollado por DANNAR NICOLE DELGADO VELASQUEZ

