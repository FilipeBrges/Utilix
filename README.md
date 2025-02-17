# Utilix

## 📌 Sobre o projeto
Utilix é um projeto desenvolvido para facilitar demandas do mundo real para pessoas que necessitam de acessbilidade ou tarefas que demandariam tempo, serem feitas mais rapidamente. Ele foi criado com o objetivo de reunir várias ferramentas em uma só aplicação.

## 🚀 Tecnologias utilizadas
O projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Kotlin**
- **Android Studio**
- **Text-to-Speech (TTS)**
- **itextpdf**
- **apachei.poi**
- **SQLite**

```

## 🔧 Como configurar e rodar o projeto

### 1️⃣ Pré-requisitos
Antes de começar, certifique-se de ter instalado:
- **JDK 17+**
- **Android Studio**
- **Git**

### 2️⃣ Clonar o repositório
```bash
git clone https://github.com/FilipeBrges/Utilix.git
```

### 3️⃣ Abrir no Android Studio
1. Abra o **Android Studio**.
2. Selecione **Open an Existing Project**.
3. Navegue até a pasta do projeto clonado e abra o arquivo.
4. Aguarde a sincronização do Gradle.

### 4️⃣ Executar o aplicativo
1. Conecte um dispositivo Android ou inicie um emulador.
2. Clique no botão **Run** (▶️) no Android Studio.

🛠 Funcionalidades principais

📌 Autenticação de usuário: Tela de login onde o usuário pode acessar sua conta usando e-mail e senha. Caso não tenha uma conta, é possível criar uma nova clicando em "Signup".

📌 Navegação por menu: Após o login, o usuário é direcionado para a tela principal do menu, onde pode escolher entre as funcionalidades disponíveis.

📌 Text-to-Speech (TTS): Permite que o usuário selecione um arquivo baixado em seu dispositivo para ser lido em voz alta pelo programa. O aplicativo abre diretamente a tela de seleção de arquivos.

📌 Navegação entre telas: Em qualquer momento, o usuário pode retornar às telas anteriores da aplicação.


⚙️ Funcionalidades pré-estabelecidas

📌 Definição do contexto: O contexto da aplicação será definido pelo grupo responsável pelo desenvolvimento.

📌 Múltiplas Activities: A aplicação possui 5 Activities distintas, cada uma com uma funcionalidade específica e claramente identificável (exemplo: tela de login, cadastro de usuário, tela de menu, tela de seleção de arquivo).

📌 Armazenamento local de dados: O Utilix utiliza um banco de dados SQLite para armazenar informações dos usuários cadastrados, garantindo que os dados sejam mantidos mesmo após o fechamento do aplicativo (os cadastros de usuarios são salvos no banco).

📌 Transições e Intents: O aplicativo usa Intents explícitas para a navegação entre as Activities (por exemplo, ao sair do login e acessar o menu principal). Os dados do usuário (como nome e e-mail) são passados entre as telas para personalizar a experiência.

---
