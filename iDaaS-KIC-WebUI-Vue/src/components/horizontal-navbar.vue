<script>
import { layoutMethods } from "@/state/helpers";
import { menuItems } from "./horizontal-menu";

export default {
  data() {
    return {
      menuItems: menuItems
    };
  },
  mounted() {
    var links = document.getElementsByClassName("side-nav-link-ref");
    var matchingMenuItem = null;
    for (var i = 0; i < links.length; i++) {
      if (window.location.pathname === links[i].pathname) {
        matchingMenuItem = links[i];
        break;
      }
    }
    if (matchingMenuItem) {
      matchingMenuItem.classList.add("active");
      var parent = matchingMenuItem.parentElement;

      /**
       * TODO: This is hard coded way of expading/activating parent menu dropdown and working till level 3.
       * We should come up with non hard coded approach
       */
      if (parent) {
        parent.classList.add("active");
        const parent2 = parent.parentElement;
        if (parent2) {
          parent2.classList.add("active");
        }
        const parent3 = parent2.parentElement;
        if (parent3) {
          parent3.classList.add("active");
          var childAnchor = parent3.querySelector(".has-dropdown");
          if (childAnchor) childAnchor.classList.add("active");
        }

        const parent4 = parent3.parentElement;
        if (parent4) parent4.classList.add("active");
        const parent5 = parent4.parentElement;
        if (parent5) parent5.classList.add("active");
      }
    }
  },
  methods: {
    ...layoutMethods,

    /**
     * Menu clicked show the submenu
     */
    onMenuClick(event) {
      event.preventDefault();
      const nextEl = event.target.nextSibling;
      if (nextEl && !nextEl.classList.contains("show")) {
        const parentEl = event.target.parentNode;
        if (parentEl) {
          parentEl.classList.remove("show");
        }
        nextEl.classList.add("show");
      } else if (nextEl) {
        nextEl.classList.remove("show");
      }
      return false;
    },
    /**
     * Returns true or false if given menu item has child or not
     * @param item menuItem
     */
    hasItems(item) {
      return item.subItems !== undefined ? item.subItems.length > 0 : false;
    },
    change_layout(layout) {
      return this.changeLayoutType({ layoutType: layout });
    },
    topbarLight() {
      document.body.setAttribute("data-topbar", "light");
      document.body.removeAttribute("data-layout-size", "boxed");
    },
    boxedWidth() {
      document.body.setAttribute("data-layout-size", "boxed");
      document.body.removeAttribute("data-topbar", "light");
      document.body.setAttribute("data-topbar", "dark");
    }
  }
};
</script>

<template>
  <div class="topnav">
    <div class="container-fluid">
      <nav class="navbar navbar-light navbar-expand-lg topnav-menu">
        <div class="collapse navbar-collapse" id="topnav-menu-content">
          <ul class="navbar-nav">
            <li class="nav-item dropdown" v-for="(item, index) of menuItems" :key="index">
              <router-link
                tag="a"
                v-if="!item.subItems"
                :to="item.link"
                class="nav-link dropdown-toggle arrow-none"
              >
                <i :class="`${item.icon} mr-2`"></i>
                {{$t(item.label)}}
              </router-link>

              <a
                v-if="item.subItems"
                class="nav-link dropdown-toggle arrow-none"
                @click="onMenuClick"
                href="javascript: void(0);"
                id="topnav-components"
                role="button"
              >
                <i :class="`${item.icon} mr-2`"></i>
                {{$t(item.label)}}
                <div class="arrow-down"></div>
              </a>
              <div
                class="dropdown-menu row"
                aria-labelledby="topnav-dashboard"
                v-if="hasItems(item)"
              >
                <template v-for="(subitem) of item.subItems">
                  <router-link
                    :key="subitem.id"
                    class="col dropdown-item side-nav-link-ref"
                    v-if="!hasItems(subitem)"
                    :to="subitem.link"
                  >{{$t(subitem.label)}}</router-link>
                  <div class="dropdown" v-if="hasItems(subitem)" :key="subitem.id">
                    <a class="dropdown-item" href="javascript: void(0);" @click="onMenuClick">
                      {{ $t(subitem.label) }}
                      <div class="arrow-down"></div>
                    </a>
                    <div class="dropdown-menu">
                      <router-link
                        v-for="(subSubitem, index) of subitem.subItems"
                        :key="index"
                        :to="subSubitem.link"
                        class="dropdown-item side-nav-link-ref"
                      >{{ $t(subSubitem.label) }}</router-link>
                    </div>
                  </div>
                </template>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle arrow-none"
                id="topnav-layout"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
                @click="onMenuClick"
              >
                <i class="ri-layout-3-line mr-2"></i>{{ $t('menuitems.layouts.text') }}
                <div class="arrow-down"></div>
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="topnav-layout">
                <a @click="change_layout('vertical')" class="dropdown-item">{{ $t('menuitems.layouts.list.vertical') }}</a>
                <a @click="topbarLight()" class="dropdown-item">{{ $t('menuitems.layouts.list.lighttopbar') }}</a>
                <a @click="boxedWidth()" class="dropdown-item">{{ $t('menuitems.layouts.list.boxed') }}</a>
              </div>
            </li>
          </ul>
        </div>
      </nav>
    </div>
  </div>
</template>