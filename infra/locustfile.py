from locust import HttpUser, TaskSet, task, between

class UserBehavior(TaskSet):
    @task
    def index(self):
        self.client.get("/")

    @task
    def actuator(self):
        self.client.get("/actuator/prometheus")

    @task
    def heavy_task(self):
        self.client.get("/heavy")

class WebsiteUser(HttpUser):
    tasks = [UserBehavior]
    wait_time = between(1, 5)
