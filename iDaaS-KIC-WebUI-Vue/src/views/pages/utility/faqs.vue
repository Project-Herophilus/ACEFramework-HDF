<script>
import Layout from "../../layouts/main";
import PageHeader from "@/components/page-header";

/**
 * FAQs component
 */
export default {
  page: {
    title: "FAQs",
    meta: [{ name: "description" }]
  },
  components: { Layout, PageHeader },
  data() {
    return {
      title: "FAQs",
      items: [
        {
          text: "Utility",
          href: "/"
        },
        {
          text: "FAQs",
          active: true
        }
      ]
    };
  }
};
</script>

<template>
  <Layout>
    <PageHeader :title="title" :items="items" />

    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="row mt-4">
              <div class="col-lg-12">
                <div class="text-center">
                  <h4>Have any Questions ?</h4>
                  <p
                    class="text-muted"
                  >It will be as simple as in fact, it will be occidental. it will seem like simplified English, as a skeptical Cambridge friend</p>

                  <div>
                    <button
                      type="button"
                      class="btn btn-primary mt-2 mr-2 waves-effect waves-light"
                    >Email Us</button>
                    <button
                      type="button"
                      class="btn btn-info mt-2 waves-effect waves-light"
                    >Send us a tweet</button>
                  </div>
                </div>
              </div>
            </div>

            <div class="row mt-5 justify-content-center">
              <div class="col-lg-10">
                <div>
                  <b-tabs pills class="faq-nav-tabs" align="center" content-class="pt-5">
                    <b-tab title="General Questions">
                      <div>
                        <div class="text-center mb-5">
                          <h5>General Questions</h5>
                          <p>Sed ut perspiciatis unde omnis iste natus error sit</p>
                        </div>

                        <div id="gen-question-accordion" class="custom-accordion-arrow">
                          <div class="card">
                            <a
                              v-b-toggle.gen-question-accordion-1
                              class="text-dark"
                              data-toggle="collapse"
                              aria-expanded="true"
                              aria-controls="gen-question-collapseOne"
                            >
                              <div class="card-header" id="gen-question-headingOne">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i>
                                  What is Lorem Ipsum ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="gen-question-accordion-1"
                              visible
                              accordion="my-accordion"
                              aria-labelledby="gen-question-headingOne"
                              data-parent="#gen-question-accordion"
                            >
                              <div
                                class="card-body"
                              >If several languages coalesce, the grammar of the resulting language is more simple and regular than that of the individual languages. The new common language will be more simple and regular than the existing</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.gen-question-accordion-2
                              data-toggle="collapse"
                              aria-expanded="false"
                              aria-controls="gen-question-collapseTwo"
                            >
                              <div class="card-header" id="gen-question-headingTwo">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Why do we use it ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="gen-question-accordion-2"
                              accordion="my-accordion"
                              aria-labelledby="gen-question-headingTwo"
                              data-parent="#gen-question-accordion"
                            >
                              <div
                                class="card-body"
                              >Everyone realizes why a new common language would be desirable one could refuse to pay expensive translators. To achieve this, it would be necessary to have uniform grammar, pronunciation and more common words.</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.gen-question-accordion-3
                              data-toggle="collapse"
                              aria-expanded="false"
                              aria-controls="gen-question-collapseThree"
                            >
                              <div class="card-header" id="gen-question-headingThree">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where can I get some ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="gen-question-accordion-3"
                              accordion="my-accordion"
                              aria-labelledby="gen-question-headingThree"
                              data-parent="#gen-question-accordion"
                            >
                              <div
                                class="card-body"
                              >Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words.</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.gen-question-accordion-4
                              data-toggle="collapse"
                              aria-expanded="false"
                              aria-controls="gen-question-collapseFour"
                            >
                              <div class="card-header" id="gen-question-headingFour">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where does it come from ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="gen-question-accordion-4"
                              accordion="my-accordion"
                              aria-labelledby="gen-question-headingFour"
                              data-parent="#gen-question-accordion"
                            >
                              <div
                                class="card-body"
                              >To an English person, it will seem like simplified English, as a skeptical Cambridge friend of mine told me what Occidental is. The European languages are members of the same family separate existence.</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.gen-question-accordion-5
                              class="collapsed"
                              data-toggle="collapse"
                              aria-expanded="false"
                              aria-controls="gen-question-collapseFive"
                            >
                              <div class="card-header" id="gen-question-headingFive">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where can I get some ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="gen-question-accordion-5"
                              accordion="my-accordion"
                              aria-labelledby="gen-question-headingFive"
                              data-parent="#gen-question-accordion"
                            >
                              <div
                                class="card-body"
                              >It will be as simple in fact, it will be occidental. To an English person, it will seem like simplified English, as a skeptical Cambridge friend of mine told me what occidental languages are members</div>
                            </b-collapse>
                          </div>
                        </div>
                      </div>
                    </b-tab>
                    <b-tab title="Privacy Policy">
                      <div>
                        <div class="text-center mb-5">
                          <h5>Privacy Policy</h5>
                          <p>Neque porro quisquam est, qui dolorem ipsum quia</p>
                        </div>

                        <div id="faq-privacy" class="custom-accordion-arrow">
                          <div class="card">
                            <a
                              v-b-toggle.faq-privacy-1
                              data-toggle="collapse"
                              aria-expanded="false"
                              aria-controls="gen-question-collapseTwo"
                            >
                              <div class="card-header" id="gen-question-headingTwo">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Why do we use it ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="faq-privacy-1"
                              accordion="privacy"
                              aria-labelledby="gen-question-headingTwo"
                              data-parent="#faq-privacy"
                            >
                              <div
                                class="card-body"
                              >Everyone realizes why a new common language would be desirable one could refuse to pay expensive translators. To achieve this, it would be necessary to have uniform grammar, pronunciation and more common words.</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.faq-privacy-2
                              class="text-dark"
                              data-toggle="collapse"
                              aria-expanded="true"
                              aria-controls="gen-question-collapseOne"
                            >
                              <div class="card-header" id="gen-question-headingOne">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i>
                                  What is Lorem Ipsum ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="faq-privacy-2"
                              visible
                              accordion="privacy"
                              aria-labelledby="gen-question-headingOne"
                              data-parent="#faq-privacy"
                            >
                              <div
                                class="card-body"
                              >If several languages coalesce, the grammar of the resulting language is more simple and regular than that of the individual languages. The new common language will be more simple and regular than the existing</div>
                            </b-collapse>
                          </div>

                          <div class="card">
                            <a
                              v-b-toggle.faq-privacy-3
                              data-toggle="collapse"
                              aria-expanded="false"
                              aria-controls="gen-question-collapseThree"
                            >
                              <div class="card-header" id="gen-question-headingThree">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where can I get some ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="faq-privacy-3"
                              accordion="privacy"
                              aria-labelledby="gen-question-headingThree"
                              data-parent="#faq-privacy"
                            >
                              <div
                                class="card-body"
                              >Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words.</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.faq-privacy-4
                              data-toggle="collapse"
                              aria-expanded="false"
                              aria-controls="gen-question-collapseFour"
                            >
                              <div class="card-header" id="gen-question-headingFour">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where does it come from ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="faq-privacy-4"
                              accordion="privacy"
                              aria-labelledby="gen-question-headingFour"
                              data-parent="#faq-privacy"
                            >
                              <div
                                class="card-body"
                              >To an English person, it will seem like simplified English, as a skeptical Cambridge friend of mine told me what Occidental is. The European languages are members of the same family separate existence.</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.faq-privacy-5
                              class="collapsed"
                              data-toggle="collapse"
                              aria-expanded="false"
                              aria-controls="gen-question-collapseFive"
                            >
                              <div class="card-header" id="gen-question-headingFive">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where can I get some ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="faq-privacy-5"
                              accordion="privacy"
                              aria-labelledby="gen-question-headingFive"
                              data-parent="#faq-privacy"
                            >
                              <div
                                class="card-body"
                              >It will be as simple in fact, it will be occidental. To an English person, it will seem like simplified English, as a skeptical Cambridge friend of mine told me what occidental languages are members</div>
                            </b-collapse>
                          </div>
                        </div>
                      </div>
                    </b-tab>
                    <b-tab title="Pricing & Plans">
                      <div>
                        <div class="text-center mb-5">
                          <h5>Pricing & Plans</h5>
                          <p>Sed ut perspiciatis unde omnis iste natus error sit</p>
                        </div>

                        <div id="pricing-accordion" class="custom-accordion-arrow">
                          <div class="card">
                            <a
                              v-b-toggle.pricing-accordion-1
                              class="text-dark"
                              data-toggle="collapse"
                              aria-expanded="true"
                              aria-controls="gen-question-collapseOne"
                            >
                              <div class="card-header" id="gen-question-headingOne">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i>
                                  What is Lorem Ipsum ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="pricing-accordion-1"
                              visible
                              accordion="pricing"
                            >
                              <div
                                class="card-body"
                              >If several languages coalesce, the grammar of the resulting language is more simple and regular than that of the individual languages. The new common language will be more simple and regular than the existing</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.pricing-accordion-2
                            >
                              <div class="card-header" id="gen-question-headingThree">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where can I get some ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="pricing-accordion-2"
                              accordion="pricing"
                            >
                              <div
                                class="card-body"
                              >Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words.</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.pricing-accordion-3
                            >
                              <div class="card-header" id="gen-question-headingTwo">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Why do we use it ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="pricing-accordion-3"
                              accordion="pricing"
                            >
                              <div
                                class="card-body"
                              >Everyone realizes why a new common language would be desirable one could refuse to pay expensive translators. To achieve this, it would be necessary to have uniform grammar, pronunciation and more common words.</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.pricing-accordion-4
                            >
                              <div class="card-header" id="gen-question-headingFive">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where can I get some ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="pricing-accordion-4"
                              accordion="pricing"
                            >
                              <div
                                class="card-body"
                              >It will be as simple in fact, it will be occidental. To an English person, it will seem like simplified English, as a skeptical Cambridge friend of mine told me what occidental languages are members</div>
                            </b-collapse>
                          </div>
                          <div class="card">
                            <a
                              v-b-toggle.pricing-accordion-5
                            >
                              <div class="card-header" id="gen-question-headingFour">
                                <h5 class="font-size-14 m-0">
                                  <i class="mdi mdi-chevron-up accor-arrow-icon"></i> Where does it come from ?
                                </h5>
                              </div>
                            </a>
                            <b-collapse
                              id="pricing-accordion-5"
                              accordion="pricing"
                            >
                              <div
                                class="card-body"
                              >To an English person, it will seem like simplified English, as a skeptical Cambridge friend of mine told me what Occidental is. The European languages are members of the same family separate existence.</div>
                            </b-collapse>
                          </div>
                        </div>
                      </div>
                    </b-tab>
                  </b-tabs>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end row -->
  </Layout>
</template>