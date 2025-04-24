# 🎓 AcademicoDB

Sistema simples de gerenciamento acadêmico, com foco em matrículas e estruturação de dados para fins educacionais e acadêmicos.

## 📌 Descrição

Este projeto tem como objetivo simular um sistema de banco de dados acadêmico, permitindo gerenciar informações como:

- Alunos
- Cursos
- Disciplinas
- Matrículas

O sistema foi desenvolvido como parte de um trabalho da disciplina de **Programação Modular** e tem como foco principal o armazenamento e manipulação de dados acadêmicos.

## 🛠️ Tecnologias Utilizadas

- Java
- PostgreSQL (ou outro banco relacional, se aplicável)
- JDBC (ou ORM, se estiver sendo utilizado)

## 📁 Estrutura do Projeto
```plaintext
└───PM2-MatriculaDB
    │   .classpath
    │   .gitignore
    │   .project
    │   pom.xml
    │
    ├───.settings
    │       org.eclipse.jdt.core.prefs
    │       org.eclipse.m2e.core.prefs
    │
    ├───src
    │   └───main
    │       ├───java
    │       │   ├───com
    │       │   │   └───projeto
    │       │   │       │   Main.java
    │       │   │       │
    │       │   │       ├───controller
    │       │   │       │       AlunoController.java
    │       │   │       │       CursoController.java
    │       │   │       │       MatriculaController.java
    │       │   │       │
    │       │   │       ├───model
    │       │   │       │       Aluno.java
    │       │   │       │       Curso.java
    │       │   │       │       Matricula.java
    │       │   │       │
    │       │   │       └───util
    │       │   │               JPAUtil.java
    │       │   │
    │       │   └───repositories
    │       │           AlunoRepository.java
    │       │
    │       └───resources
    │           └───META-INF
    │                   hibernate.xml
    │                   persistence.xml
    │
    └───target
        ├───classes
        │   ├───com
        │   │   └───projeto
        │   │       │   Main.class
        │   │       │
        │   │       ├───controller
        │   │       │       AlunoController.class
        │   │       │       CursoController.class
        │   │       │       MatriculaController.class
        │   │       │
        │   │       ├───model
        │   │       │       Aluno.class
        │   │       │       Curso.class
        │   │       │       Matricula.class
        │   │       │
        │   │       └───util
        │   │               JPAUtil.class
        │   │
        │   ├───META-INF
        │   │   │   hibernate.xml
        │   │   │   MANIFEST.MF
        │   │   │   persistence.xml
        │   │   │
        │   │   └───maven
        │   │       └───com.projeto
        │   │           └───matricula-console
        │   │                   pom.properties
        │   │                   pom.xml
        │   │
        │   └───repositories
        │           AlunoRepository.class
        │
        └───test-classes


## ⚙️ Como Executar

1. Clone este repositório:
 git clone https://github.com/Pedro-b0t/AcademicoDB.git 
 
2. Abra o projeto em sua IDE Java de preferência.

3. Certifique-se de que o banco de dados está configurado corretamente:

4. Crie o banco de dados.

5. Compile e execute a ação.

📄 Scripts SQL

A pasta banco-de-dados/ contém os scripts necessários para criar as tabelas e popular o banco de dados com dados iniciais.

🚀 Funcionalidades
Cadastro e consulta de alunos

- Cadastro de cursos e disciplinas

- Realização de matrículas

- Relatórios simples (opcional)

📚 Objetivos de Aprendizado
Prática com modelagem de dados

- Aplicação de conceitos de programação modular.

- Conexão entre Java e banco de dados relacional.

- Manipulação de dados usando SQL.

## 👨‍💻 Autores

Desenvolvido por:
- Pedro ([Github](https://github.com/Pedro-b0t))
- Victor ([Github](https://github.com/victorsilv19))

