from locust import HttpUser, TaskSet, task, between

class UserBehavior(TaskSet):
    @task
    def index(self):
        self.client.get("/process")

    @task
    def heavy_task(self):
        self.client.get("/compute")

    @task
    def heavy_task(self):
        self.client.get("/db")

class WebsiteUser(HttpUser):
    tasks = [UserBehavior]
    wait_time = between(1, 5)
