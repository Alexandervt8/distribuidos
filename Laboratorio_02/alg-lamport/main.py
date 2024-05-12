import multiprocessing
import time
import random

class LamportClock:
    def __init__(self):
        self.clock = multiprocessing.Value('i', 0)

    def tick(self):
        with self.clock.get_lock():
            self.clock.value += 1
            return self.clock.value

    def update(self, received_time):
        with self.clock.get_lock():
            self.clock.value = max(self.clock.value, received_time) + 1

    def get_time(self):
        with self.clock.get_lock():
            return self.clock.value

def main():
    processes = []
    clock = LamportClock()

    def process_function():
        time_value = clock.tick()
        print(f"Process {multiprocessing.current_process().name} created event with Lamport time {time_value}")
        time.sleep(random.random())
        received_time = clock.tick()
        print(f"Process {multiprocessing.current_process().name} received event with Lamport time {received_time}")
        clock.update(received_time)

    for _ in range(5):
        process = multiprocessing.Process(target=process_function)
        processes.append(process)
        process.start()

    for process in processes:
        process.join()

    print("Final Lamport time:", clock.get_time())

if __name__ == "__main__":
    main()
