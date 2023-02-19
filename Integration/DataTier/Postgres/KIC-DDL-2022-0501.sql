// DDL
create table app_intgrtn_insight
(
    appintgrtn_insightid integer   default nextval('appintgrtn_insight_appintgrtn_insightid_seq'::regclass) not null
        constraint app_intgrtn_insightid_pk
            primary key,
    messagedate          varchar(25),
    messagetime          varchar(25),
    processingtype       varchar(25),
    industrystd          varchar(20),
    component            varchar(75),
    messagetrigger       varchar(25),
    processname          varchar(55),
    auditdetails         varchar(199),
    camelid              varchar(49),
    exchangeid           varchar(49),
    internalmsgid        varchar(49),
    errorid              varchar(49),
    errordata            text,
    requesttype          varchar(49),
    transactioncount     integer,
    transactiondirection varchar(49),
    auditentiremessage   text,
    bodydata             text,
    createddate          timestamp default CURRENT_TIMESTAMP
);

create unique index app_intgrtn_insight_uindex
    on app_intgrtn_insight (appintgrtn_insightid);

create index app_intgrtn_insight_indx
    on app_intgrtn_insight (appintgrtn_insightid, messagedate, messagetime, processingtype, industrystd, component,
                            messagetrigger, processname, auditdetails, camelid, exchangeid, internalmsgid, errorid,
                            errordata, requesttype, transactioncount, transactiondirection)

create table data_intgrtn_insight
(
    intgrtn_insightid  integer   default nextval('intgrtn_insight_intgrtn_insightid_seq'::regclass) not null
        constraint data_intgrtn_insightid_pk
            primary key,
    messagedate        varchar(25),
    messagetime        varchar(25),
    processingtype     varchar(25),
    industrystd        varchar(20),
    component          varchar(75),
    messagetrigger     varchar(25),
    processname        varchar(55),
    auditdetails       varchar(199),
    camelid            varchar(49),
    exchangeid         varchar(49),
    internalmsgid      varchar(49),
    auditentiremessage text,
    bodydata           text,
    createddate        timestamp default CURRENT_TIMESTAMP
);

create unique index data_intgrtn_insight_uindex
    on data_intgrtn_insight (intgrtn_insightid);

create index data_intgrtn_insight_indx
    on data_intgrtn_insight (intgrtn_insightid, messagedate, messagetime, processingtype, industrystd, component,
                             messagetrigger, processname, auditdetails, camelid, exchangeid, internalmsgid);
