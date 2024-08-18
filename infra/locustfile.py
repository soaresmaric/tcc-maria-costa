from locust import HttpUser, TaskSet, task, between

from locust import HttpUser, task, between

class ColdStartSimulation(HttpUser):
    wait_time = between(1, 5)  # Aguarda entre 1 e 5 segundos entre as requisições

    @task(1)
    def compute(self):
        self.client.get("/compute")

    @task(1)
    def process(self):
        self.client.get("/process")

    @task(1)
    def interact_with_db(self):
        self.client.get("/db")
    
    @task(1)
    def interact_memory_leak(self):
        self.client.get("/memory-leak")

    def on_start(self):
        # Simula o cold start aguardando por um tempo maior na inicialização
        self.client.get("/compute")
        self.client.get("/process")
        self.client.get("/db")
        self.client.get("/memory-leak")
