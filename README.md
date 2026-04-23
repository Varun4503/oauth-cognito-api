## Backend (Spring Boot Resource Server)

This service acts as an OAuth 2.0 Resource Server. It validates JWT access tokens issued by AWS Cognito and secures REST APIs using Spring Security.

- Validates JWT using Cognito issuer URI
- Protects endpoints with authentication
- Extracts user claims from token

Frontend Repo: https://github.com/your-username/oauth-cognito-react-client
