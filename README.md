# Bank System

FEITOS!

controller:
 - TransactionController (camada controller de transferencia)
 - UserController
 
domain.transaction:
 - Transaction

domain.user:
 - User
 - UserType contendo um enum com "COMMON" e "MERCHANT"

dtos: (records)
 - ExceptionDTO
 - NotificationDTO
 - TransactionDTO

infra:
 - AppConfig
 - ControllerExceptionHandler

picpaysimplificado:
 - PicpaysimplificadoApplication

repositories: (interfaces)
 - TransactionRepository (interface vazia)
 - UserRepository

services:
 - NotificationService
 - TransactionService
 - UserService
 - authentication (autenticação de usuários) Feito na camada UserService e UserController
 - authorization (autorização de usuários) Feito na camada TransactionService

A FAZER!

- security:
  - encryption (criptografia de dados sensíveis) feitos alguns métodos mas não implementado no código
  - validation (validações de entrada)

- audit:
  - logging (registro de operações e alterações de dados)
  - compliance (conformidade regulatória)
  - suspicious_activity (detecção e registro de atividades suspeitas)

- reports:
  - financial_activities (relatórios de atividades financeiras)
  - account_balances (relatórios de saldos de contas)
  - spending_trends (relatórios de tendências de gastos)

- error_management:
  - exception_handling (tratamento de exceções)
  - error_logging (registro de erros)
  - error_resolution (resolução de problemas)

- internationalization:
  - languages (suporte a diferentes idiomas)
  - currencies (suporte a diferentes moedas)
  - date_formatting (formato de data/hora)

- additional_features:
  - transfers (transferências entre contas)
  - payments (pagamentos)
  - scheduled_payments (agendamento de pagamentos)
  - investments (investimentos)
  - loans (empréstimos e financiamentos)
  - real_time_notifications (notificações em tempo real)
  - budget_management (gestão de orçamento)
