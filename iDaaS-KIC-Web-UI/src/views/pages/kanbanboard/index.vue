<script>
import draggable from "vuedraggable";

import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";
import { todoTasks, progressTasks, completedTasks } from "./data";

/**
 * Kanban Board Component
 */
export default {
  page: {
    title: "Kanban Board",
    meta: [{ name: "description" }],
  },
  components: { draggable, Layout, PageHeader },
  data() {
    return {
      todoTasks: todoTasks,
      progressTasks: progressTasks,
      completedTasks: completedTasks,
      title: "Kanban Board",
      items: [
        {
          text: "Nazox",
          href: "/",
        },
        {
          text: "Kanban Board",
          active: true,
        },
      ],
    };
  },
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />
    <div class="row mb-2">
      <div class="col-lg-6">
        <div class="media">
          <div class="mr-3">
            <img
              src="@/assets/images/logo-sm-light.png"
              alt
              class="avatar-xs"
            />
          </div>
          <div class="media-body">
            <h5>Nazox admin Dashboard</h5>
            <span class="badge badge-soft-success">Open</span>
          </div>
        </div>
      </div>
      <div class="col-lg-6">
        <div class="text-lg-right">
          <ul class="list-inline mb-0">
            <li class="list-inline-item">
              <a
                href="javascript: void(0);"
                class="d-inline-block"
                v-b-tooltip.hover
                title="Aaron Williams"
              >
                <img
                  src="@/assets/images/users/avatar-2.jpg"
                  class="rounded-circle avatar-xs"
                  alt
                />
              </a>
            </li>
            <li class="list-inline-item">
              <a
                href="javascript: void(0);"
                class="d-inline-block"
                v-b-tooltip.hover
                title="Jonathan McKinney"
              >
                <div class="avatar-xs">
                  <span
                    class="avatar-title rounded-circle bg-soft-primary text-primary"
                    >J</span
                  >
                </div>
              </a>
            </li>
            <li class="list-inline-item">
              <a
                href="javascript: void(0);"
                class="d-inline-block"
                v-b-tooltip.hover
                title="Carole Connolly"
              >
                <img
                  src="@/assets/images/users/avatar-4.jpg"
                  class="rounded-circle avatar-xs"
                  alt
                />
              </a>
            </li>

            <li class="list-inline-item">
              <a href="javascript: void(0);" class="py-1">
                <i class="mdi mdi-plus mr-1"></i> New member
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-4">
        <div class="card-body">
          <b-dropdown right class="float-right" variant="white">
            <template v-slot:button-content>
              <i class="mdi mdi-dots-vertical m-0 text-muted font-size-20"></i>
            </template>
            <b-dropdown-item>Edit</b-dropdown-item>
            <b-dropdown-item>Delete</b-dropdown-item>
          </b-dropdown>

          <!-- end dropdown -->
          <h4 class="card-title">Todo</h4>
          <p class="mb-0">3 Tasks</p>
        </div>
        <div class="card">
          <div class="card-body border-bottom">
            <div id="todo-task" class="task-list">
              <draggable class="list-group" group="tasks" :list="todoTasks">
                <div
                  v-for="task in todoTasks"
                  :key="task.id"
                  class="card task-box"
                >
                  <div
                    class="progress progress-sm animated-progess"
                    style="height: 3px"
                  >
                    <div
                      class="progress-bar"
                      role="progressbar"
                      style="width: 72%"
                      aria-valuenow="72"
                      aria-valuemin="0"
                      aria-valuemax="100"
                    ></div>
                  </div>
                  <div class="card-body">
                    <div class="float-right ml-2">
                      <div>{{ task.date }}</div>
                    </div>
                    <div class="mb-3">
                      <a href="#" class>{{ task.id }}</a>
                    </div>
                    <div>
                      <h5 class="font-size-16">
                        <a href="javascript: void(0);" class="text-dark">{{
                          task.title
                        }}</a>
                      </h5>
                      <p class="mb-4">{{ task.text }}</p>
                    </div>

                    <div class="d-inline-flex team mb-0">
                      <div class="mr-3 align-self-center">Team :</div>
                      <div class="team-member">
                        <a
                          href="javascript: void(0);"
                          class="team-member d-inline-block"
                          v-b-tooltip.hover
                          data-placement="top"
                          title="Calvin Redrick"
                        >
                          <img
                            :src="`${task.user[0]}`"
                            class="rounded-circle avatar-xs"
                            alt
                          />
                        </a>
                      </div>

                      <div class="team-member">
                        <a
                          href="javascript: void(0);"
                          class="team-member d-inline-block"
                          v-b-tooltip.hover
                          data-placement="top"
                          title="David Martinez"
                          v-if="task.user[1]"
                        >
                          <img
                            :src="`${task.user[1]}`"
                            class="rounded-circle avatar-xs"
                            alt
                          />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </draggable>
              <!-- end task card -->

              <div class="text-center">
                <a
                  href="javascript: void(0);"
                  class="btn btn-primary mt-1 waves-effect waves-light"
                >
                  <i class="mdi mdi-plus mr-1"></i> Add New
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="card-body">
          <b-dropdown right class="float-right" variant="white">
            <template v-slot:button-content>
              <i class="mdi mdi-dots-vertical m-0 text-muted font-size-20"></i>
            </template>
            <b-dropdown-item>Edit</b-dropdown-item>
            <b-dropdown-item>Delete</b-dropdown-item>
          </b-dropdown>
          <!-- end dropdown -->
          <h4 class="card-title">In Progress</h4>
          <p class="mb-0">3 Tasks</p>
        </div>
        <div class="card">
          <div class="card-body border-bottom">
            <div id="inprogress-task" class="task-list">
              <draggable class="list-group" group="tasks" :list="progressTasks">
                <div
                  class="card task-box"
                  v-for="task in progressTasks"
                  :key="task.id"
                >
                  <div
                    class="progress progress-sm animated-progess"
                    style="height: 3px"
                  >
                    <div
                      class="progress-bar"
                      role="progressbar"
                      style="width: 72%"
                      aria-valuenow="72"
                      aria-valuemin="0"
                      aria-valuemax="100"
                    ></div>
                  </div>
                  <div class="card-body">
                    <div class="float-right ml-2">
                      <div>{{ task.date }}</div>
                    </div>
                    <div class="mb-3">
                      <a href="#" class>{{ task.id }}</a>
                    </div>
                    <div>
                      <h5 class="font-size-16">
                        <a href="javascript: void(0);" class="text-dark">{{
                          task.title
                        }}</a>
                      </h5>
                      <p class="mb-4">{{ task.text }}</p>
                    </div>

                    <div class="d-inline-flex team mb-0">
                      <div class="mr-3 align-self-center">Team :</div>
                      <div class="team-member">
                        <a
                          href="javascript: void(0);"
                          class="team-member d-inline-block"
                          v-b-tooltip.hover
                          data-placement="top"
                          title="Calvin Redrick"
                        >
                          <img
                            :src="`${task.user[0]}`"
                            class="rounded-circle avatar-xs"
                            alt
                          />
                        </a>
                      </div>

                      <div class="team-member">
                        <a
                          href="javascript: void(0);"
                          class="team-member d-inline-block"
                          v-b-tooltip.hover
                          data-placement="top"
                          title="David Martinez"
                          v-if="task.user[1]"
                        >
                          <img
                            :src="`${task.user[1]}`"
                            class="rounded-circle avatar-xs"
                            alt
                          />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- end task card -->
              </draggable>
              <div class="text-center">
                <a
                  href="javascript: void(0);"
                  class="btn btn-primary mt-1 waves-effect waves-light"
                >
                  <i class="mdi mdi-plus mr-1"></i> Add New
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="card-body">
          <b-dropdown right class="float-right" variant="white">
            <template v-slot:button-content>
              <i class="mdi mdi-dots-vertical m-0 text-muted font-size-20"></i>
            </template>
            <b-dropdown-item>Edit</b-dropdown-item>
            <b-dropdown-item>Delete</b-dropdown-item>
          </b-dropdown>
          <!-- end dropdown -->
          <h4 class="card-title">Completed</h4>
          <p class="mb-0">3 Tasks</p>
        </div>
        <div class="card">
          <div class="card-body border-bottom">
            <div id="inprogress-task" class="task-list">
              <draggable
                class="list-group"
                group="tasks"
                :list="completedTasks"
              >
                <div
                  class="card task-box"
                  v-for="task in completedTasks"
                  :key="task.id"
                >
                  <div
                    class="progress progress-sm animated-progess"
                    style="height: 3px"
                  >
                    <div
                      class="progress-bar"
                      role="progressbar"
                      style="width: 72%"
                      aria-valuenow="72"
                      aria-valuemin="0"
                      aria-valuemax="100"
                    ></div>
                  </div>
                  <div class="card-body">
                    <div class="float-right ml-2">
                      <div>{{ task.date }}</div>
                    </div>
                    <div class="mb-3">
                      <a href="#" class>{{ task.id }}</a>
                    </div>
                    <div>
                      <h5 class="font-size-16">
                        <a href="javascript: void(0);" class="text-dark">{{
                          task.title
                        }}</a>
                      </h5>
                      <p class="mb-4">{{ task.text }}</p>
                    </div>

                    <div class="d-inline-flex team mb-0">
                      <div class="mr-3 align-self-center">Team :</div>
                      <div class="team-member">
                        <a
                          href="javascript: void(0);"
                          class="team-member d-inline-block"
                          v-b-tooltip.hover
                          title="Calvin Redrick"
                        >
                          <img
                            :src="`${task.user[0]}`"
                            class="rounded-circle avatar-xs"
                            alt
                          />
                        </a>
                      </div>

                      <div class="team-member">
                        <a
                          href="javascript: void(0);"
                          class="team-member d-inline-block"
                          v-b-tooltip.hover
                          title="David Martinez"
                          v-if="task.user[1]"
                        >
                          <img
                            :src="`${task.user[1]}`"
                            class="rounded-circle avatar-xs"
                            alt
                          />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- end task card -->
              </draggable>
              <div class="text-center">
                <a
                  href="javascript: void(0);"
                  class="btn btn-primary mt-1 waves-effect waves-light"
                >
                  <i class="mdi mdi-plus mr-1"></i> Add New
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </Layout>
</template>