package se.nrm.dina.datamodel;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import se.nrm.dina.datamodel.Accession;
import se.nrm.dina.datamodel.Accessionagent;
import se.nrm.dina.datamodel.Accessionattachment;
import se.nrm.dina.datamodel.Accessionauthorization;
import se.nrm.dina.datamodel.Address;
import se.nrm.dina.datamodel.Addressofrecord;
import se.nrm.dina.datamodel.Agent;
import se.nrm.dina.datamodel.Agentattachment;
import se.nrm.dina.datamodel.Agentgeography;
import se.nrm.dina.datamodel.Agentspecialty;
import se.nrm.dina.datamodel.Agentvariant;
import se.nrm.dina.datamodel.Appraisal;
import se.nrm.dina.datamodel.Attachment;
import se.nrm.dina.datamodel.Attachmentmetadata;
import se.nrm.dina.datamodel.Attachmenttag;
import se.nrm.dina.datamodel.Attributedef;
import se.nrm.dina.datamodel.Author;
import se.nrm.dina.datamodel.Autonumberingscheme;
import se.nrm.dina.datamodel.Borrow;
import se.nrm.dina.datamodel.Borrowagent;
import se.nrm.dina.datamodel.Borrowmaterial;
import se.nrm.dina.datamodel.Borrowreturnmaterial;
import se.nrm.dina.datamodel.Collectingevent;
import se.nrm.dina.datamodel.Collectingeventattachment;
import se.nrm.dina.datamodel.Collectingeventattr;
import se.nrm.dina.datamodel.Collectingeventattribute;
import se.nrm.dina.datamodel.Collectingtrip;
import se.nrm.dina.datamodel.Collection;
import se.nrm.dina.datamodel.Collectionobject;
import se.nrm.dina.datamodel.Collectionobjectattachment;
import se.nrm.dina.datamodel.Collectionobjectattr;
import se.nrm.dina.datamodel.Collectionobjectattribute;
import se.nrm.dina.datamodel.Collectionobjectcitation;
import se.nrm.dina.datamodel.Collectionrelationship;
import se.nrm.dina.datamodel.Collectionreltype;
import se.nrm.dina.datamodel.Collector;
import se.nrm.dina.datamodel.Commonnametx;
import se.nrm.dina.datamodel.Commonnametxcitation;
import se.nrm.dina.datamodel.Conservdescription;
import se.nrm.dina.datamodel.Conservdescriptionattachment;
import se.nrm.dina.datamodel.Conservevent;
import se.nrm.dina.datamodel.Conserveventattachment;
import se.nrm.dina.datamodel.Container;
import se.nrm.dina.datamodel.Datatype;
import se.nrm.dina.datamodel.Deaccession;
import se.nrm.dina.datamodel.Deaccessionagent;
import se.nrm.dina.datamodel.Deaccessionpreparation;
import se.nrm.dina.datamodel.Determination;
import se.nrm.dina.datamodel.Determinationcitation;
import se.nrm.dina.datamodel.Discipline;
import se.nrm.dina.datamodel.Division;
import se.nrm.dina.datamodel.Dnasequence;
import se.nrm.dina.datamodel.Dnasequenceattachment;
import se.nrm.dina.datamodel.Dnasequencerunattachment;
import se.nrm.dina.datamodel.Dnasequencingrun;
import se.nrm.dina.datamodel.Dnasequencingruncitation;
import se.nrm.dina.datamodel.Exchangein;
import se.nrm.dina.datamodel.Exchangeout;
import se.nrm.dina.datamodel.Exsiccata;
import se.nrm.dina.datamodel.Exsiccataitem;
import se.nrm.dina.datamodel.Fieldnotebook;
import se.nrm.dina.datamodel.Fieldnotebookattachment;
import se.nrm.dina.datamodel.Fieldnotebookpage;
import se.nrm.dina.datamodel.Fieldnotebookpageattachment;
import se.nrm.dina.datamodel.Fieldnotebookpageset;
import se.nrm.dina.datamodel.Fieldnotebookpagesetattachment;
import se.nrm.dina.datamodel.Geocoorddetail;
import se.nrm.dina.datamodel.Geography;
import se.nrm.dina.datamodel.Geographytreedef;
import se.nrm.dina.datamodel.Geographytreedefitem;
import se.nrm.dina.datamodel.Geologictimeperiod;
import se.nrm.dina.datamodel.Geologictimeperiodtreedef;
import se.nrm.dina.datamodel.Geologictimeperiodtreedefitem;
import se.nrm.dina.datamodel.Gift;
import se.nrm.dina.datamodel.Giftagent;
import se.nrm.dina.datamodel.Giftpreparation;
import se.nrm.dina.datamodel.Groupperson;
import se.nrm.dina.datamodel.Inforequest;
import se.nrm.dina.datamodel.Institution;
import se.nrm.dina.datamodel.Journal;
import se.nrm.dina.datamodel.Lithostrat;
import se.nrm.dina.datamodel.Lithostrattreedef;
import se.nrm.dina.datamodel.Lithostrattreedefitem;
import se.nrm.dina.datamodel.Loan;
import se.nrm.dina.datamodel.Loanagent;
import se.nrm.dina.datamodel.Loanattachment;
import se.nrm.dina.datamodel.Loanpreparation;
import se.nrm.dina.datamodel.Loanreturnpreparation;
import se.nrm.dina.datamodel.Locality;
import se.nrm.dina.datamodel.Localityattachment;
import se.nrm.dina.datamodel.Localitycitation;
import se.nrm.dina.datamodel.Localitydetail;
import se.nrm.dina.datamodel.Localitynamealias;
import se.nrm.dina.datamodel.Otheridentifier;
import se.nrm.dina.datamodel.Paleocontext;
import se.nrm.dina.datamodel.Permit;
import se.nrm.dina.datamodel.Permitattachment;
import se.nrm.dina.datamodel.Picklist;
import se.nrm.dina.datamodel.Picklistitem;
import se.nrm.dina.datamodel.Preparation;
import se.nrm.dina.datamodel.Preparationattachment;
import se.nrm.dina.datamodel.Preparationattr;
import se.nrm.dina.datamodel.Preparationattribute;
import se.nrm.dina.datamodel.Preptype;
import se.nrm.dina.datamodel.Project;
import se.nrm.dina.datamodel.Recordset;
import se.nrm.dina.datamodel.Referencework;
import se.nrm.dina.datamodel.Repositoryagreement;
import se.nrm.dina.datamodel.Repositoryagreementattachment;
import se.nrm.dina.datamodel.Shipment;
import se.nrm.dina.datamodel.Spappresource;
import se.nrm.dina.datamodel.Spappresourcedata;
import se.nrm.dina.datamodel.Spappresourcedir;
import se.nrm.dina.datamodel.Spauditlog;
import se.nrm.dina.datamodel.Spauditlogfield;
import se.nrm.dina.datamodel.Specifyuser;
import se.nrm.dina.datamodel.Spexportschema;
import se.nrm.dina.datamodel.Spexportschemaitem;
import se.nrm.dina.datamodel.Spexportschemaitemmapping;
import se.nrm.dina.datamodel.Spexportschemamapping;
import se.nrm.dina.datamodel.Spfieldvaluedefault;
import se.nrm.dina.datamodel.Splocalecontainer;
import se.nrm.dina.datamodel.Splocalecontaineritem;
import se.nrm.dina.datamodel.Splocaleitemstr;
import se.nrm.dina.datamodel.Spprincipal;
import se.nrm.dina.datamodel.Spquery;
import se.nrm.dina.datamodel.Spqueryfield;
import se.nrm.dina.datamodel.Spreport;
import se.nrm.dina.datamodel.Spsymbiotainstance;
import se.nrm.dina.datamodel.Sptasksemaphore;
import se.nrm.dina.datamodel.Spversion;
import se.nrm.dina.datamodel.Spviewsetobj;
import se.nrm.dina.datamodel.Storage;
import se.nrm.dina.datamodel.Storageattachment;
import se.nrm.dina.datamodel.Storagetreedef;
import se.nrm.dina.datamodel.Storagetreedefitem;
import se.nrm.dina.datamodel.Taxon;
import se.nrm.dina.datamodel.Taxonattachment;
import se.nrm.dina.datamodel.Taxoncitation;
import se.nrm.dina.datamodel.Taxontreedef;
import se.nrm.dina.datamodel.Taxontreedefitem;
import se.nrm.dina.datamodel.Treatmentevent;
import se.nrm.dina.datamodel.Workbench;
import se.nrm.dina.datamodel.Workbenchtemplate;
import se.nrm.dina.datamodel.Workbenchtemplatemappingitem;

@Generated(value="EclipseLink-2.6.0.v20130922-rNA", date="2016-04-11T16:29:46")
@StaticMetamodel(Agent.class)
public class Agent_ extends BaseEntity_ {

    public static volatile ListAttribute<Agent, Preptype> preptypeList1;
    public static volatile ListAttribute<Agent, Taxon> taxonList1;
    public static volatile ListAttribute<Agent, Commonnametx> commonnametxList1;
    public static volatile ListAttribute<Agent, Preptype> preptypeList;
    public static volatile ListAttribute<Agent, Spviewsetobj> spviewsetobjList1;
    public static volatile ListAttribute<Agent, Lithostrattreedef> lithostrattreedefList;
    public static volatile ListAttribute<Agent, Storagetreedefitem> storagetreedefitemList1;
    public static volatile ListAttribute<Agent, Conservdescriptionattachment> conservdescriptionattachmentList1;
    public static volatile ListAttribute<Agent, Institution> institutionList1;
    public static volatile ListAttribute<Agent, Deaccessionpreparation> deaccessionpreparationList;
    public static volatile ListAttribute<Agent, Determination> determinationList;
    public static volatile ListAttribute<Agent, Paleocontext> paleocontextList1;
    public static volatile ListAttribute<Agent, Fieldnotebookpagesetattachment> fieldnotebookpagesetattachmentList1;
    public static volatile ListAttribute<Agent, Dnasequencerunattachment> dnasequencerunattachmentList;
    public static volatile ListAttribute<Agent, Collectionobjectattr> collectionobjectattrList;
    public static volatile ListAttribute<Agent, Dnasequencingrun> dnasequencingrunList2;
    public static volatile ListAttribute<Agent, Spappresourcedir> spappresourcedirList;
    public static volatile ListAttribute<Agent, Agentgeography> agentgeographyList;
    public static volatile ListAttribute<Agent, Dnasequencingrun> dnasequencingrunList3;
    public static volatile ListAttribute<Agent, Giftpreparation> giftpreparationList;
    public static volatile ListAttribute<Agent, Dnasequencingrun> dnasequencingrunList1;
    public static volatile ListAttribute<Agent, Geologictimeperiodtreedef> geologictimeperiodtreedefList;
    public static volatile ListAttribute<Agent, Geographytreedef> geographytreedefList1;
    public static volatile ListAttribute<Agent, Referencework> referenceworkList1;
    public static volatile ListAttribute<Agent, Picklist> picklistList1;
    public static volatile ListAttribute<Agent, Dnasequence> dnasequenceList2;
    public static volatile ListAttribute<Agent, Lithostrattreedefitem> lithostrattreedefitemList;
    public static volatile ListAttribute<Agent, Collectingtrip> collectingtripList;
    public static volatile ListAttribute<Agent, Dnasequence> dnasequenceList1;
    public static volatile SingularAttribute<Agent, Integer> agentID;
    public static volatile ListAttribute<Agent, Loanattachment> loanattachmentList;
    public static volatile ListAttribute<Agent, Spprincipal> spprincipalList;
    public static volatile ListAttribute<Agent, Permitattachment> permitattachmentList1;
    public static volatile ListAttribute<Agent, Agentspecialty> agentspecialtyList1;
    public static volatile ListAttribute<Agent, Agentspecialty> agentspecialtyList2;
    public static volatile ListAttribute<Agent, Sptasksemaphore> sptasksemaphoreList1;
    public static volatile ListAttribute<Agent, Division> divisionList1;
    public static volatile ListAttribute<Agent, Spexportschema> spexportschemaList1;
    public static volatile SingularAttribute<Agent, Short> dateOfBirthPrecision;
    public static volatile ListAttribute<Agent, Storagetreedef> storagetreedefList1;
    public static volatile ListAttribute<Agent, Locality> localityList;
    public static volatile ListAttribute<Agent, Spfieldvaluedefault> spfieldvaluedefaultList;
    public static volatile ListAttribute<Agent, Collectingeventattr> collectingeventattrList1;
    public static volatile ListAttribute<Agent, Attributedef> attributedefList;
    public static volatile ListAttribute<Agent, Fieldnotebookpage> fieldnotebookpageList1;
    public static volatile ListAttribute<Agent, Addressofrecord> addressofrecordList;
    public static volatile SingularAttribute<Agent, Date> dateOfDeath;
    public static volatile ListAttribute<Agent, Preparationattachment> preparationattachmentList1;
    public static volatile ListAttribute<Agent, Geologictimeperiodtreedefitem> geologictimeperiodtreedefitemList1;
    public static volatile ListAttribute<Agent, Splocalecontaineritem> splocalecontaineritemList1;
    public static volatile ListAttribute<Agent, Datatype> datatypeList;
    public static volatile ListAttribute<Agent, Repositoryagreement> repositoryagreementList;
    public static volatile ListAttribute<Agent, Storagetreedefitem> storagetreedefitemList;
    public static volatile ListAttribute<Agent, Spquery> spqueryList1;
    public static volatile ListAttribute<Agent, Localitynamealias> localitynamealiasList1;
    public static volatile ListAttribute<Agent, Loanagent> loanagentList;
    public static volatile ListAttribute<Agent, Paleocontext> paleocontextList;
    public static volatile ListAttribute<Agent, Repositoryagreement> repositoryagreementList1;
    public static volatile SingularAttribute<Agent, String> middleInitial;
    public static volatile ListAttribute<Agent, Taxon> taxonList;
    public static volatile ListAttribute<Agent, Repositoryagreement> repositoryagreementList2;
    public static volatile ListAttribute<Agent, Preparation> preparationList;
    public static volatile SingularAttribute<Agent, String> email;
    public static volatile ListAttribute<Agent, Dnasequence> dnasequenceList;
    public static volatile ListAttribute<Agent, Institution> institutionList;
    public static volatile SingularAttribute<Agent, String> abbreviation;
    public static volatile ListAttribute<Agent, Borrowagent> borrowagentList;
    public static volatile ListAttribute<Agent, Collectingeventattribute> collectingeventattributeList1;
    public static volatile ListAttribute<Agent, Collectionobjectattr> collectionobjectattrList1;
    public static volatile ListAttribute<Agent, Workbenchtemplatemappingitem> workbenchtemplatemappingitemList1;
    public static volatile ListAttribute<Agent, Collectionobject> collectionobjectList1;
    public static volatile ListAttribute<Agent, Conservdescription> conservdescriptionList1;
    public static volatile ListAttribute<Agent, Collectionobject> collectionobjectList2;
    public static volatile ListAttribute<Agent, Geography> geographyList;
    public static volatile ListAttribute<Agent, Collectingevent> collectingeventList;
    public static volatile ListAttribute<Agent, Agentvariant> agentvariantList1;
    public static volatile ListAttribute<Agent, Spsymbiotainstance> spsymbiotainstanceList1;
    public static volatile ListAttribute<Agent, Otheridentifier> otheridentifierList1;
    public static volatile ListAttribute<Agent, Permit> permitList;
    public static volatile ListAttribute<Agent, Exchangeout> exchangeoutList;
    public static volatile ListAttribute<Agent, Agentvariant> agentvariantList2;
    public static volatile ListAttribute<Agent, Fieldnotebookpageset> fieldnotebookpagesetList;
    public static volatile ListAttribute<Agent, Attributedef> attributedefList1;
    public static volatile ListAttribute<Agent, Borrow> borrowList;
    public static volatile ListAttribute<Agent, Specifyuser> specifyuserList1;
    public static volatile ListAttribute<Agent, Localitydetail> localitydetailList1;
    public static volatile ListAttribute<Agent, Workbench> workbenchList1;
    public static volatile ListAttribute<Agent, Appraisal> appraisalList2;
    public static volatile ListAttribute<Agent, Appraisal> appraisalList1;
    public static volatile ListAttribute<Agent, Loan> loanList1;
    public static volatile ListAttribute<Agent, Spappresourcedir> spappresourcedirList1;
    public static volatile ListAttribute<Agent, Addressofrecord> addressofrecordList2;
    public static volatile ListAttribute<Agent, Accessionagent> accessionagentList2;
    public static volatile ListAttribute<Agent, Accessionagent> accessionagentList1;
    public static volatile ListAttribute<Agent, Conservevent> conserveventList;
    public static volatile ListAttribute<Agent, Addressofrecord> addressofrecordList1;
    public static volatile ListAttribute<Agent, Groupperson> grouppersonList3;
    public static volatile ListAttribute<Agent, Groupperson> grouppersonList2;
    public static volatile ListAttribute<Agent, Geographytreedef> geographytreedefList;
    public static volatile ListAttribute<Agent, Sptasksemaphore> sptasksemaphoreList;
    public static volatile ListAttribute<Agent, Project> projectList;
    public static volatile ListAttribute<Agent, Dnasequenceattachment> dnasequenceattachmentList1;
    public static volatile ListAttribute<Agent, Localitycitation> localitycitationList;
    public static volatile ListAttribute<Agent, Groupperson> grouppersonList1;
    public static volatile ListAttribute<Agent, Storageattachment> storageattachmentList;
    public static volatile ListAttribute<Agent, Taxoncitation> taxoncitationList1;
    public static volatile ListAttribute<Agent, Discipline> disciplineList;
    public static volatile ListAttribute<Agent, Giftagent> giftagentList2;
    public static volatile ListAttribute<Agent, Giftagent> giftagentList1;
    public static volatile ListAttribute<Agent, Attachmenttag> attachmenttagList1;
    public static volatile ListAttribute<Agent, Taxontreedef> taxontreedefList1;
    public static volatile SingularAttribute<Agent, String> firstName;
    public static volatile ListAttribute<Agent, Geography> geographyList1;
    public static volatile ListAttribute<Agent, Author> authorList;
    public static volatile ListAttribute<Agent, Preparationattr> preparationattrList;
    public static volatile SingularAttribute<Agent, String> guid;
    public static volatile ListAttribute<Agent, Spversion> spversionList;
    public static volatile ListAttribute<Agent, Commonnametxcitation> commonnametxcitationList1;
    public static volatile ListAttribute<Agent, Fieldnotebookpageattachment> fieldnotebookpageattachmentList1;
    public static volatile ListAttribute<Agent, Spexportschemaitem> spexportschemaitemList1;
    public static volatile ListAttribute<Agent, Taxontreedefitem> taxontreedefitemList;
    public static volatile ListAttribute<Agent, Container> containerList1;
    public static volatile ListAttribute<Agent, Geologictimeperiodtreedefitem> geologictimeperiodtreedefitemList;
    public static volatile ListAttribute<Agent, Spsymbiotainstance> spsymbiotainstanceList;
    public static volatile ListAttribute<Agent, Treatmentevent> treatmenteventList1;
    public static volatile ListAttribute<Agent, Exsiccata> exsiccataList;
    public static volatile ListAttribute<Agent, Borrowmaterial> borrowmaterialList;
    public static volatile ListAttribute<Agent, Spexportschemaitemmapping> spexportschemaitemmappingList1;
    public static volatile SingularAttribute<Agent, String> title;
    public static volatile SingularAttribute<Agent, Short> dateOfDeathPrecision;
    public static volatile ListAttribute<Agent, Referencework> referenceworkList;
    public static volatile SingularAttribute<Agent, Short> dateType;
    public static volatile ListAttribute<Agent, Picklistitem> picklistitemList;
    public static volatile ListAttribute<Agent, Fieldnotebookpageattachment> fieldnotebookpageattachmentList;
    public static volatile ListAttribute<Agent, Spreport> spreportList1;
    public static volatile ListAttribute<Agent, Journal> journalList;
    public static volatile ListAttribute<Agent, Exchangein> exchangeinList;
    public static volatile ListAttribute<Agent, Groupperson> grouppersonList;
    public static volatile ListAttribute<Agent, Permit> permitList3;
    public static volatile ListAttribute<Agent, Autonumberingscheme> autonumberingschemeList;
    public static volatile ListAttribute<Agent, Collection> collectionList;
    public static volatile ListAttribute<Agent, Spqueryfield> spqueryfieldList;
    public static volatile ListAttribute<Agent, Permit> permitList1;
    public static volatile ListAttribute<Agent, Recordset> recordsetList;
    public static volatile ListAttribute<Agent, Borrowagent> borrowagentList2;
    public static volatile ListAttribute<Agent, Permit> permitList2;
    public static volatile ListAttribute<Agent, Borrowagent> borrowagentList1;
    public static volatile ListAttribute<Agent, Spexportschemaitemmapping> spexportschemaitemmappingList;
    public static volatile ListAttribute<Agent, Preparationattribute> preparationattributeList;
    public static volatile ListAttribute<Agent, Specifyuser> specifyuserList;
    public static volatile ListAttribute<Agent, Loanpreparation> loanpreparationList1;
    public static volatile ListAttribute<Agent, Taxontreedefitem> taxontreedefitemList1;
    public static volatile ListAttribute<Agent, Spquery> spqueryList;
    public static volatile ListAttribute<Agent, Localitydetail> localitydetailList;
    public static volatile ListAttribute<Agent, Shipment> shipmentList;
    public static volatile ListAttribute<Agent, Spversion> spversionList1;
    public static volatile ListAttribute<Agent, Gift> giftList1;
    public static volatile ListAttribute<Agent, Lithostrattreedef> lithostrattreedefList1;
    public static volatile ListAttribute<Agent, Shipment> shipmentList4;
    public static volatile ListAttribute<Agent, Shipment> shipmentList3;
    public static volatile ListAttribute<Agent, Otheridentifier> otheridentifierList;
    public static volatile ListAttribute<Agent, Shipment> shipmentList2;
    public static volatile ListAttribute<Agent, Fieldnotebookpagesetattachment> fieldnotebookpagesetattachmentList;
    public static volatile ListAttribute<Agent, Shipment> shipmentList1;
    public static volatile ListAttribute<Agent, Accessionattachment> accessionattachmentList;
    public static volatile ListAttribute<Agent, Lithostrat> lithostratList1;
    public static volatile ListAttribute<Agent, Fieldnotebook> fieldnotebookList;
    public static volatile ListAttribute<Agent, Geologictimeperiod> geologictimeperiodList;
    public static volatile ListAttribute<Agent, Accessionagent> accessionagentList;
    public static volatile ListAttribute<Agent, Workbenchtemplatemappingitem> workbenchtemplatemappingitemList;
    public static volatile ListAttribute<Agent, Autonumberingscheme> autonumberingschemeList1;
    public static volatile ListAttribute<Agent, Dnasequencingruncitation> dnasequencingruncitationList1;
    public static volatile ListAttribute<Agent, Storagetreedef> storagetreedefList;
    public static volatile ListAttribute<Agent, Address> addressList1;
    public static volatile ListAttribute<Agent, Splocaleitemstr> splocaleitemstrList1;
    public static volatile ListAttribute<Agent, Storage> storageList;
    public static volatile ListAttribute<Agent, Collectingevent> collectingeventList1;
    public static volatile ListAttribute<Agent, Collectionobjectattribute> collectionobjectattributeList1;
    public static volatile ListAttribute<Agent, Collectionobjectcitation> collectionobjectcitationList1;
    public static volatile ListAttribute<Agent, Collectionrelationship> collectionrelationshipList1;
    public static volatile ListAttribute<Agent, Datatype> datatypeList1;
    public static volatile ListAttribute<Agent, Agentattachment> agentattachmentList2;
    public static volatile ListAttribute<Agent, Agentattachment> agentattachmentList1;
    public static volatile ListAttribute<Agent, Collectingeventattr> collectingeventattrList;
    public static volatile ListAttribute<Agent, Spappresourcedata> spappresourcedataList1;
    public static volatile ListAttribute<Agent, Localityattachment> localityattachmentList;
    public static volatile ListAttribute<Agent, Exsiccata> exsiccataList1;
    public static volatile ListAttribute<Agent, Collectionreltype> collectionreltypeList;
    public static volatile ListAttribute<Agent, Spexportschemaitem> spexportschemaitemList;
    public static volatile ListAttribute<Agent, Accessionauthorization> accessionauthorizationList;
    public static volatile ListAttribute<Agent, Attachment> attachmentList1;
    public static volatile ListAttribute<Agent, Exsiccataitem> exsiccataitemList1;
    public static volatile ListAttribute<Agent, Lithostrat> lithostratList;
    public static volatile ListAttribute<Agent, Loan> loanList;
    public static volatile ListAttribute<Agent, Spappresourcedata> spappresourcedataList;
    public static volatile ListAttribute<Agent, Geologictimeperiod> geologictimeperiodList1;
    public static volatile ListAttribute<Agent, Dnasequencingrun> dnasequencingrunList;
    public static volatile ListAttribute<Agent, Conservdescriptionattachment> conservdescriptionattachmentList;
    public static volatile ListAttribute<Agent, Geographytreedefitem> geographytreedefitemList;
    public static volatile ListAttribute<Agent, Loanreturnpreparation> loanreturnpreparationList2;
    public static volatile ListAttribute<Agent, Loanreturnpreparation> loanreturnpreparationList1;
    public static volatile ListAttribute<Agent, Deaccessionagent> deaccessionagentList2;
    public static volatile ListAttribute<Agent, Accession> accessionList1;
    public static volatile ListAttribute<Agent, Deaccessionagent> deaccessionagentList1;
    public static volatile ListAttribute<Agent, Conserveventattachment> conserveventattachmentList1;
    public static volatile ListAttribute<Agent, Borrow> borrowList1;
    public static volatile ListAttribute<Agent, Journal> journalList1;
    public static volatile ListAttribute<Agent, Borrowreturnmaterial> borrowreturnmaterialList;
    public static volatile SingularAttribute<Agent, Agent> createdByAgentID;
    public static volatile ListAttribute<Agent, Agent> agentList2;
    public static volatile SingularAttribute<Agent, Institution> institutionCCID;
    public static volatile ListAttribute<Agent, Agent> agentList1;
    public static volatile ListAttribute<Agent, Repositoryagreementattachment> repositoryagreementattachmentList;
    public static volatile SingularAttribute<Agent, String> lastName;
    public static volatile SingularAttribute<Agent, Short> agentType;
    public static volatile ListAttribute<Agent, Accessionattachment> accessionattachmentList1;
    public static volatile ListAttribute<Agent, Determination> determinationList1;
    public static volatile ListAttribute<Agent, Determination> determinationList2;
    public static volatile ListAttribute<Agent, Collectingtrip> collectingtripList1;
    public static volatile ListAttribute<Agent, Attachmentmetadata> attachmentmetadataList;
    public static volatile ListAttribute<Agent, Geocoorddetail> geocoorddetailList1;
    public static volatile ListAttribute<Agent, Locality> localityList1;
    public static volatile ListAttribute<Agent, Project> projectList1;
    public static volatile ListAttribute<Agent, Spauditlog> spauditlogList;
    public static volatile ListAttribute<Agent, Geocoorddetail> geocoorddetailList2;
    public static volatile ListAttribute<Agent, Project> projectList2;
    public static volatile ListAttribute<Agent, Commonnametx> commonnametxList;
    public static volatile ListAttribute<Agent, Attachmentmetadata> attachmentmetadataList1;
    public static volatile ListAttribute<Agent, Inforequest> inforequestList;
    public static volatile ListAttribute<Agent, Accession> accessionList;
    public static volatile ListAttribute<Agent, Loanpreparation> loanpreparationList;
    public static volatile ListAttribute<Agent, Appraisal> appraisalList;
    public static volatile ListAttribute<Agent, Conserveventattachment> conserveventattachmentList;
    public static volatile ListAttribute<Agent, Inforequest> inforequestList2;
    public static volatile ListAttribute<Agent, Inforequest> inforequestList1;
    public static volatile ListAttribute<Agent, Taxontreedef> taxontreedefList;
    public static volatile ListAttribute<Agent, Borrowmaterial> borrowmaterialList1;
    public static volatile ListAttribute<Agent, Address> addressList;
    public static volatile ListAttribute<Agent, Loanagent> loanagentList1;
    public static volatile ListAttribute<Agent, Loanagent> loanagentList2;
    public static volatile ListAttribute<Agent, Accessionauthorization> accessionauthorizationList1;
    public static volatile ListAttribute<Agent, Dnasequencingruncitation> dnasequencingruncitationList;
    public static volatile ListAttribute<Agent, Fieldnotebook> fieldnotebookList1;
    public static volatile ListAttribute<Agent, Spviewsetobj> spviewsetobjList;
    public static volatile ListAttribute<Agent, Fieldnotebook> fieldnotebookList2;
    public static volatile ListAttribute<Agent, Preparation> preparationList2;
    public static volatile ListAttribute<Agent, Preparation> preparationList1;
    public static volatile SingularAttribute<Agent, Date> dateOfBirth;
    public static volatile ListAttribute<Agent, Collectionreltype> collectionreltypeList1;
    public static volatile SingularAttribute<Agent, String> url;
    public static volatile ListAttribute<Agent, Conservdescription> conservdescriptionList;
    public static volatile ListAttribute<Agent, Address> addressList2;
    public static volatile ListAttribute<Agent, Collection> collectionList1;
    public static volatile ListAttribute<Agent, Dnasequenceattachment> dnasequenceattachmentList;
    public static volatile ListAttribute<Agent, Gift> giftList;
    public static volatile ListAttribute<Agent, Recordset> recordsetList1;
    public static volatile ListAttribute<Agent, Fieldnotebookattachment> fieldnotebookattachmentList;
    public static volatile ListAttribute<Agent, Collectingeventattribute> collectingeventattributeList;
    public static volatile ListAttribute<Agent, Permitattachment> permitattachmentList;
    public static volatile SingularAttribute<Agent, String> interests;
    public static volatile ListAttribute<Agent, Exchangeout> exchangeoutList1;
    public static volatile ListAttribute<Agent, Exchangeout> exchangeoutList2;
    public static volatile ListAttribute<Agent, Exchangeout> exchangeoutList3;
    public static volatile ListAttribute<Agent, Storageattachment> storageattachmentList1;
    public static volatile ListAttribute<Agent, Deaccession> deaccessionList1;
    public static volatile ListAttribute<Agent, Giftagent> giftagentList;
    public static volatile ListAttribute<Agent, Exsiccataitem> exsiccataitemList;
    public static volatile ListAttribute<Agent, Container> containerList;
    public static volatile ListAttribute<Agent, Collectingeventattachment> collectingeventattachmentList;
    public static volatile SingularAttribute<Agent, Specifyuser> specifyUserID;
    public static volatile SingularAttribute<Agent, String> jobTitle;
    public static volatile ListAttribute<Agent, Picklistitem> picklistitemList1;
    public static volatile ListAttribute<Agent, Determinationcitation> determinationcitationList;
    public static volatile SingularAttribute<Agent, Agent> modifiedByAgentID;
    public static volatile ListAttribute<Agent, Taxonattachment> taxonattachmentList1;
    public static volatile SingularAttribute<Agent, String> suffix;
    public static volatile ListAttribute<Agent, Localitynamealias> localitynamealiasList;
    public static volatile ListAttribute<Agent, Lithostrattreedefitem> lithostrattreedefitemList1;
    public static volatile ListAttribute<Agent, Spprincipal> spprincipalList1;
    public static volatile ListAttribute<Agent, Collectingeventattachment> collectingeventattachmentList1;
    public static volatile SingularAttribute<Agent, Institution> institutionTCID;
    public static volatile ListAttribute<Agent, Repositoryagreementattachment> repositoryagreementattachmentList1;
    public static volatile SingularAttribute<Agent, Collection> collectionCCID;
    public static volatile ListAttribute<Agent, Agentgeography> agentgeographyList1;
    public static volatile ListAttribute<Agent, Preparationattr> preparationattrList1;
    public static volatile ListAttribute<Agent, Spauditlogfield> spauditlogfieldList1;
    public static volatile ListAttribute<Agent, Agentgeography> agentgeographyList2;
    public static volatile ListAttribute<Agent, Giftpreparation> giftpreparationList1;
    public static volatile ListAttribute<Agent, Geocoorddetail> geocoorddetailList;
    public static volatile ListAttribute<Agent, Geographytreedefitem> geographytreedefitemList1;
    public static volatile ListAttribute<Agent, Division> divisionList;
    public static volatile SingularAttribute<Agent, String> initials;
    public static volatile ListAttribute<Agent, Collectionobjectattachment> collectionobjectattachmentList;
    public static volatile SingularAttribute<Agent, Agent> parentOrganizationID;
    public static volatile ListAttribute<Agent, Collector> collectorList;
    public static volatile ListAttribute<Agent, Loanreturnpreparation> loanreturnpreparationList;
    public static volatile ListAttribute<Agent, Agentattachment> agentattachmentList;
    public static volatile ListAttribute<Agent, Collectionobject> collectionobjectList;
    public static volatile ListAttribute<Agent, Taxoncitation> taxoncitationList;
    public static volatile ListAttribute<Agent, Spappresource> spappresourceList1;
    public static volatile ListAttribute<Agent, Collectionobjectattachment> collectionobjectattachmentList1;
    public static volatile SingularAttribute<Agent, Division> divisionID;
    public static volatile ListAttribute<Agent, Agentspecialty> agentspecialtyList;
    public static volatile ListAttribute<Agent, Collectionobjectcitation> collectionobjectcitationList;
    public static volatile ListAttribute<Agent, Fieldnotebookpageset> fieldnotebookpagesetList2;
    public static volatile ListAttribute<Agent, Workbenchtemplate> workbenchtemplateList;
    public static volatile ListAttribute<Agent, Fieldnotebookpageset> fieldnotebookpagesetList1;
    public static volatile ListAttribute<Agent, Preparationattribute> preparationattributeList1;
    public static volatile ListAttribute<Agent, Spappresource> spappresourceList;
    public static volatile ListAttribute<Agent, Workbench> workbenchList;
    public static volatile ListAttribute<Agent, Preparationattachment> preparationattachmentList;
    public static volatile ListAttribute<Agent, Picklist> picklistList;
    public static volatile ListAttribute<Agent, Localitycitation> localitycitationList1;
    public static volatile ListAttribute<Agent, Spqueryfield> spqueryfieldList1;
    public static volatile ListAttribute<Agent, Collectionobjectattribute> collectionobjectattributeList;
    public static volatile ListAttribute<Agent, Splocalecontainer> splocalecontainerList;
    public static volatile ListAttribute<Agent, Discipline> disciplineList1;
    public static volatile ListAttribute<Agent, Spauditlog> spauditlogList1;
    public static volatile ListAttribute<Agent, Geologictimeperiodtreedef> geologictimeperiodtreedefList1;
    public static volatile ListAttribute<Agent, Attachment> attachmentList;
    public static volatile ListAttribute<Agent, Treatmentevent> treatmenteventList;
    public static volatile ListAttribute<Agent, Conservevent> conserveventList4;
    public static volatile ListAttribute<Agent, Fieldnotebookattachment> fieldnotebookattachmentList1;
    public static volatile ListAttribute<Agent, Spexportschemamapping> spexportschemamappingList;
    public static volatile ListAttribute<Agent, Deaccessionpreparation> deaccessionpreparationList1;
    public static volatile ListAttribute<Agent, Exchangein> exchangeinList3;
    public static volatile ListAttribute<Agent, Agent> agentList;
    public static volatile ListAttribute<Agent, Exchangein> exchangeinList2;
    public static volatile ListAttribute<Agent, Exchangein> exchangeinList1;
    public static volatile ListAttribute<Agent, Conservevent> conserveventList1;
    public static volatile ListAttribute<Agent, Conservevent> conserveventList2;
    public static volatile ListAttribute<Agent, Conservevent> conserveventList3;
    public static volatile SingularAttribute<Agent, Collection> collectionTCID;
    public static volatile ListAttribute<Agent, Splocaleitemstr> splocaleitemstrList;
    public static volatile ListAttribute<Agent, Attachmenttag> attachmenttagList;
    public static volatile ListAttribute<Agent, Spexportschema> spexportschemaList;
    public static volatile ListAttribute<Agent, Fieldnotebookpage> fieldnotebookpageList;
    public static volatile ListAttribute<Agent, Commonnametxcitation> commonnametxcitationList;
    public static volatile ListAttribute<Agent, Spfieldvaluedefault> spfieldvaluedefaultList1;
    public static volatile ListAttribute<Agent, Deaccession> deaccessionList;
    public static volatile ListAttribute<Agent, Deaccessionagent> deaccessionagentList;
    public static volatile ListAttribute<Agent, Spauditlogfield> spauditlogfieldList;
    public static volatile ListAttribute<Agent, Determinationcitation> determinationcitationList1;
    public static volatile ListAttribute<Agent, Workbenchtemplate> workbenchtemplateList1;
    public static volatile ListAttribute<Agent, Collector> collectorList2;
    public static volatile ListAttribute<Agent, Collector> collectorList1;
    public static volatile ListAttribute<Agent, Splocalecontaineritem> splocalecontaineritemList;
    public static volatile ListAttribute<Agent, Spexportschemamapping> spexportschemamappingList1;
    public static volatile ListAttribute<Agent, Storage> storageList1;
    public static volatile ListAttribute<Agent, Borrowreturnmaterial> borrowreturnmaterialList2;
    public static volatile ListAttribute<Agent, Dnasequencerunattachment> dnasequencerunattachmentList1;
    public static volatile ListAttribute<Agent, Collectionrelationship> collectionrelationshipList;
    public static volatile ListAttribute<Agent, Localityattachment> localityattachmentList1;
    public static volatile ListAttribute<Agent, Author> authorList2;
    public static volatile ListAttribute<Agent, Borrowreturnmaterial> borrowreturnmaterialList1;
    public static volatile ListAttribute<Agent, Author> authorList1;
    public static volatile ListAttribute<Agent, Spreport> spreportList;
    public static volatile ListAttribute<Agent, Agentvariant> agentvariantList;
    public static volatile ListAttribute<Agent, Taxonattachment> taxonattachmentList;
    public static volatile SingularAttribute<Agent, String> remarks;
    public static volatile ListAttribute<Agent, Splocalecontainer> splocalecontainerList1;
    public static volatile ListAttribute<Agent, Loanattachment> loanattachmentList1;

}