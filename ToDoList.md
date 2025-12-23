# To Do List

1) Generar front básico junto a BD
1.1) Página inicial para hacer el login
1.2) Leer y guardar usuario y contraseña (con hash) de la BD
1.3) Primera página tras login
1.3.1) Formulario para añadir nuevos productos (compra realizada)
1.3.2) Introducir los productos que se deben comprar 
1.3.3) Botón "generar lista de compra" (generar un pdf)
1.3.4) Historial compras

2) Inicio del back
2.1) Lo relacionado con la lectura y aceptación de usuarios y nuevos registros
2.2) Permitir añadir nueva compra
2.3) Permitir indicar que se necesita comprar
2.4) Generar pdf que contenga la lista de productos necesarias
2.5) Mostrar en el front lo que indica la bd

3) Estrucutra BD
Tabla Users:
| campo         | tipo     | notas              |
| ------------- | -------- | ------------------ |
| id            | PK int   |                    |
| username      | varchar  | único              |
| email         | varchar  | único              |
| password      | varchar  | hash bcrypt/argon2 |

Tabla Items:
| campo        | tipo     | notas                |
| ------------ | -------- | -------------------- |
| id           | PK int   |                      |
| user_id      | FK int   | referencia a `users` |
| name         | text     |                      |
| quantity     | text     | opcional             |
| category     | text     | opcional             |
| price        | text     | opcional             |
| added_at     | datetime |                      |
| is_completed | boolean  | false por defecto    |

Tabla Purchases:
| campo        | tipo     | notas               |
| ------------ | -------- | --------------------|
| id           | PK int   |                     |
| user_id      | FK int   | referencia a `users`|
| item_name    | text     | copia del nombre    |
| quantity     | text     | opcional            |
| purchased_at | datetime |                     |
