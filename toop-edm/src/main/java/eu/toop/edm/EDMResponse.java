/**
 * This work is protected under copyrights held by the members of the
 * TOOP Project Consortium as indicated at
 * http://wiki.ds.unipi.gr/display/TOOP/Contributors
 * (c) 2018-2021. All rights reserved.
 *
 * This work is licensed under the EUPL 1.2.
 *
 *  = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 *
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved
 * by the European Commission - subsequent versions of the EUPL
 * (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *         https://joinup.ec.europa.eu/software/page/eupl
 */
package eu.toop.edm;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.w3c.dom.Node;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.traits.IGenericImplTrait;
import com.helger.regrep.ERegRepResponseStatus;
import com.helger.regrep.RegRep4Reader;
import com.helger.regrep.RegRep4Writer;
import com.helger.regrep.RegRepHelper;
import com.helger.regrep.query.QueryResponse;
import com.helger.regrep.rim.AnyValueType;
import com.helger.regrep.rim.CollectionValueType;
import com.helger.regrep.rim.DateTimeValueType;
import com.helger.regrep.rim.ExtrinsicObjectType;
import com.helger.regrep.rim.ObjectRefListType;
import com.helger.regrep.rim.ObjectRefType;
import com.helger.regrep.rim.RegistryObjectListType;
import com.helger.regrep.rim.RegistryObjectType;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.rim.StringValueType;
import com.helger.regrep.rim.ValueType;
import com.helger.regrep.slot.ISlotProvider;

import eu.toop.edm.jaxb.cccev.CCCEVConceptType;
import eu.toop.edm.jaxb.cv.agent.AgentType;
import eu.toop.edm.model.AgentPojo;
import eu.toop.edm.model.ConceptPojo;
import eu.toop.edm.model.EToopResponseOptionType;
import eu.toop.edm.response.EDMResponsePayloadConcepts;
import eu.toop.edm.response.IEDMResponsePayloadProvider;
import eu.toop.edm.response.ResponseDocumentPojo;
import eu.toop.edm.response.ResponseDocumentReferencePojo;
import eu.toop.edm.slot.SlotConceptValues;
import eu.toop.edm.slot.SlotDataProvider;
import eu.toop.edm.slot.SlotIssueDateTime;
import eu.toop.edm.slot.SlotSpecificationIdentifier;
import eu.toop.edm.xml.IJAXBVersatileReader;
import eu.toop.edm.xml.IVersatileWriter;
import eu.toop.edm.xml.JAXBVersatileReader;
import eu.toop.edm.xml.JAXBVersatileWriter;
import eu.toop.edm.xml.cagv.AgentMarshaller;
import eu.toop.edm.xml.cccev.CCCEV;
import eu.toop.edm.xml.cccev.ConceptMarshaller;

/**
 * This class contains the data model for a single TOOP EDM Request. It requires
 * at least the following fields:
 * <ul>
 * <li>ResponseOption - "Registry Object" or "Object Reference"?</li>
 * <li>Response Status - Success, partial success or failure</li>
 * <li>Request ID - the ID of the request to which this response
 * correlates.</li>
 * <li>Specification Identifier - must be the value
 * {@link CToopEDM#SPECIFICATION_IDENTIFIER_TOOP_EDM_V21}.</li>
 * <li>Issue date time - when the response was created. Ideally in UTC.</li>
 * <li>Data Provider - the basic infos of the DP</li>
 * <li>If it is a "ConceptQuery" the response Concepts must be provided.</li>
 * <li>If it is a "DocumentQuery" the response Dataset must be provided.</li>
 * </ul>
 * It is recommended to use the <code>builder*()</code> methods to create the
 * EDM request using the builder pattern with a fluent API.
 *
 * @author Philip Helger
 * @author Konstantinos Douloudis
 */
public class EDMResponse implements IEDMTopLevelObject
{
  private static final ICommonsOrderedSet <String> TOP_LEVEL_SLOTS = new CommonsLinkedHashSet <> (SlotSpecificationIdentifier.NAME,
                                                                                                  SlotIssueDateTime.NAME,
                                                                                                  SlotDataProvider.NAME);

  private final EToopResponseOptionType m_eResponseOption;
  private final ERegRepResponseStatus m_eResponseStatus;
  private final String m_sRequestID;
  private final String m_sSpecificationIdentifier;
  private final LocalDateTime m_aIssueDateTime;
  private final AgentPojo m_aDataProvider;
  private final ICommonsList <IEDMResponsePayloadProvider> m_aPayloadProviders = new CommonsArrayList <> ();

  protected EDMResponse (@Nonnull final EToopResponseOptionType eResponseOption,
                         @Nonnull final ERegRepResponseStatus eResponseStatus,
                         @Nonnull @Nonempty final String sRequestID,
                         @Nonnull @Nonempty final String sSpecificationIdentifier,
                         @Nonnull final LocalDateTime aIssueDateTime,
                         @Nonnull final AgentPojo aDataProvider,
                         @Nonnull @Nonempty final ICommonsList <? extends IEDMResponsePayloadProvider> aPayloadProviders)
  {
    ValueEnforcer.notNull (eResponseOption, "ResponseOption");
    ValueEnforcer.notNull (eResponseStatus, "ResponseStatus");
    ValueEnforcer.isTrue (eResponseStatus == ERegRepResponseStatus.SUCCESS || eResponseStatus == ERegRepResponseStatus.FAILURE,
                          "Only SUCCESS and FAILURE are supported");
    ValueEnforcer.notEmpty (sRequestID, "RequestID");
    ValueEnforcer.notEmpty (sSpecificationIdentifier, "SpecificationIdentifier");
    ValueEnforcer.notNull (aIssueDateTime, "IssueDateTime");
    ValueEnforcer.notNull (aDataProvider, "DataProvider");
    ValueEnforcer.notEmptyNoNullValue (aPayloadProviders, "PayloadProviders");

    m_eResponseOption = eResponseOption;
    m_eResponseStatus = eResponseStatus;
    m_sRequestID = sRequestID;
    m_sSpecificationIdentifier = sSpecificationIdentifier;
    m_aIssueDateTime = aIssueDateTime;
    m_aDataProvider = aDataProvider;
    m_aPayloadProviders.addAll (aPayloadProviders);
  }

  @Nonnull
  public final EToopResponseOptionType getResponseOption ()
  {
    return m_eResponseOption;
  }

  @Nonnull
  public final ERegRepResponseStatus getResponseStatus ()
  {
    return m_eResponseStatus;
  }

  @Nonnull
  @Nonempty
  public final String getRequestID ()
  {
    return m_sRequestID;
  }

  @Nonnull
  @Nonempty
  public final String getSpecificationIdentifier ()
  {
    return m_sSpecificationIdentifier;
  }

  @Nonnull
  public final LocalDateTime getIssueDateTime ()
  {
    return m_aIssueDateTime;
  }

  @Nonnull
  public final AgentPojo getDataProvider ()
  {
    return m_aDataProvider;
  }

  /**
   * @return The payload providers. Never <code>null</code> but maybe empty. The
   *         payload elements are either
   *         {@link eu.toop.edm.response.IEDMResponsePayloadConcepts},
   *         {@link eu.toop.edm.response.IEDMResponsePayloadDocument} or
   *         {@link eu.toop.edm.response.IEDMResponsePayloadDocumentReference}.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableObject
  public final List <IEDMResponsePayloadProvider> payloadProviders ()
  {
    return m_aPayloadProviders;
  }

  /**
   * @return The payload providers. Never <code>null</code> but maybe empty. The
   *         payload elements are either
   *         {@link eu.toop.edm.response.IEDMResponsePayloadConcepts},
   *         {@link eu.toop.edm.response.IEDMResponsePayloadDocument} or
   *         {@link eu.toop.edm.response.IEDMResponsePayloadDocumentReference}.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public final List <IEDMResponsePayloadProvider> getAllPayloadProviders ()
  {
    return m_aPayloadProviders.getClone ();
  }

  @Nonnull
  private QueryResponse _createQueryResponse (@Nonnull final ICommonsList <ISlotProvider> aProviders)
  {
    final ICommonsOrderedMap <String, ISlotProvider> aProviderMap = new CommonsLinkedHashMap <> ();
    for (final ISlotProvider aItem : aProviders)
    {
      final String sName = aItem.getName ();
      if (aProviderMap.containsKey (sName))
        throw new IllegalArgumentException ("A slot provider for name '" + sName + "' is already present");
      aProviderMap.put (sName, aItem);
    }

    final QueryResponse ret = RegRepHelper.createEmptyQueryResponse (m_eResponseStatus);
    ret.setRequestId (m_sRequestID);

    // All top-level slots outside of object list
    for (final String sHeader : TOP_LEVEL_SLOTS)
    {
      final ISlotProvider aSP = aProviderMap.get (sHeader);
      if (aSP != null)
        ret.addSlot (aSP.createSlot ());
    }

    switch (m_eResponseOption)
    {
      case INLINE:
        final RegistryObjectListType aROList = new RegistryObjectListType ();
        for (final IEDMResponsePayloadProvider aItem : m_aPayloadProviders)
          aROList.addRegistryObject (aItem.getAsRegistryObject ());
        ret.setRegistryObjectList (aROList);
        break;
      case REFERENCE:
        final ObjectRefListType aORList = new ObjectRefListType ();
        for (final IEDMResponsePayloadProvider aItem : m_aPayloadProviders)
          aORList.addObjectRef (aItem.getAsObjectRef ());
        ret.setObjectRefList (aORList);
        break;
      default:
        throw new IllegalStateException ("Found unsupported ResponseOption " + m_eResponseOption);
    }

    return ret;
  }

  @Nonnull
  public QueryResponse getAsQueryResponse ()
  {
    final ICommonsList <ISlotProvider> aSlots = new CommonsArrayList <> ();
    if (m_sSpecificationIdentifier != null)
      aSlots.add (new SlotSpecificationIdentifier (m_sSpecificationIdentifier));
    if (m_aIssueDateTime != null)
      aSlots.add (new SlotIssueDateTime (m_aIssueDateTime));
    if (m_aDataProvider != null)
      aSlots.add (new SlotDataProvider (m_aDataProvider));

    return _createQueryResponse (aSlots);
  }

  @Nonnull
  public IVersatileWriter <QueryResponse> getWriter ()
  {
    return new JAXBVersatileWriter <> (getAsQueryResponse (), RegRep4Writer.queryResponse (CCCEV.XSDS).setFormattedOutput (true));
  }

  @Nonnull
  public static IJAXBVersatileReader <EDMResponse> reader ()
  {
    return new JAXBVersatileReader <> (RegRep4Reader.queryResponse (CCCEV.XSDS), EDMResponse::create);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || getClass () != o.getClass ())
      return false;
    final EDMResponse that = (EDMResponse) o;
    return EqualsHelper.equals (m_eResponseOption, that.m_eResponseOption) &&
           EqualsHelper.equals (m_eResponseStatus, that.m_eResponseStatus) &&
           EqualsHelper.equals (m_sRequestID, that.m_sRequestID) &&
           EqualsHelper.equals (m_sSpecificationIdentifier, that.m_sSpecificationIdentifier) &&
           EqualsHelper.equals (m_aIssueDateTime, that.m_aIssueDateTime) &&
           EqualsHelper.equals (m_aDataProvider, that.m_aDataProvider) &&
           EqualsHelper.equals (m_aPayloadProviders, that.m_aPayloadProviders);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eResponseOption)
                                       .append (m_eResponseStatus)
                                       .append (m_sRequestID)
                                       .append (m_sSpecificationIdentifier)
                                       .append (m_aIssueDateTime)
                                       .append (m_aDataProvider)
                                       .append (m_aPayloadProviders)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ResponseOption", m_eResponseOption)
                                       .append ("RequestID", m_sRequestID)
                                       .append ("ResponseStatus", m_eResponseStatus)
                                       .append ("SpecificationIdentifier", m_sSpecificationIdentifier)
                                       .append ("IssueDateTime", m_aIssueDateTime)
                                       .append ("DataProvider", m_aDataProvider)
                                       .append ("ResponseObjects", m_aPayloadProviders)
                                       .getToString ();
  }

  @Nonnull
  public static BuilderConcept builderConcept ()
  {
    // RegistryObjectID doesn't matter for concepts but must be settable in
    // import for comparison
    return new BuilderConcept ().specificationIdentifier (CToopEDM.SPECIFICATION_IDENTIFIER_TOOP_EDM_V21).randomRegistryObjectID ();
  }

  @Nonnull
  public static BuilderDocument builderDocument ()
  {
    return new BuilderDocument ().specificationIdentifier (CToopEDM.SPECIFICATION_IDENTIFIER_TOOP_EDM_V21);
  }

  @Nonnull
  public static BuilderDocumentReference builderDocumentReference ()
  {
    return new BuilderDocumentReference ().specificationIdentifier (CToopEDM.SPECIFICATION_IDENTIFIER_TOOP_EDM_V21);
  }

  /**
   * Abstract builder for the main builders
   *
   * @author Philip Helger
   * @param <T>
   *        The effective builder type
   */
  public abstract static class AbstractBuilder <T extends AbstractBuilder <T>> implements IGenericImplTrait <T>
  {
    protected EToopResponseOptionType m_eResponseOption;
    protected ERegRepResponseStatus m_eResponseStatus;
    protected String m_sRequestID;
    protected String m_sSpecificationIdentifier;
    protected LocalDateTime m_aIssueDateTime;
    protected AgentPojo m_aDataProvider;

    protected AbstractBuilder (@Nonnull final EToopResponseOptionType e)
    {
      ValueEnforcer.notNull (e, "ResponseOption");
      m_eResponseOption = e;
    }

    @Nonnull
    public final T responseStatus (@Nullable final ERegRepResponseStatus e)
    {
      m_eResponseStatus = e;
      return thisAsT ();
    }

    @Nonnull
    public final T requestID (@Nullable final UUID a)
    {
      return requestID (a == null ? null : a.toString ());
    }

    @Nonnull
    public final T requestID (@Nullable final String s)
    {
      m_sRequestID = s;
      return thisAsT ();
    }

    @Nonnull
    public final T specificationIdentifier (@Nullable final String s)
    {
      m_sSpecificationIdentifier = s;
      return thisAsT ();
    }

    @Nonnull
    public final T issueDateTimeNow ()
    {
      return issueDateTime (PDTFactory.getCurrentLocalDateTime ());
    }

    @Nonnull
    public final T issueDateTime (@Nullable final LocalDateTime a)
    {
      m_aIssueDateTime = a == null ? null : a.truncatedTo (ChronoUnit.MILLIS);
      return thisAsT ();
    }

    @Nonnull
    public final T dataProvider (@Nullable final Consumer <? super AgentPojo.Builder> a)
    {
      if (a != null)
      {
        final AgentPojo.Builder aBuilder = AgentPojo.builder ();
        a.accept (aBuilder);
        dataProvider (aBuilder.build ());
      }
      return thisAsT ();
    }

    @Nonnull
    public final T dataProvider (@Nullable final AgentPojo.Builder a)
    {
      return dataProvider (a == null ? null : a.build ());
    }

    @Nonnull
    public final T dataProvider (@Nullable final AgentPojo a)
    {
      m_aDataProvider = a;
      return thisAsT ();
    }

    @Nonnull
    public final T dataProvider (@Nullable final AgentType a)
    {
      return dataProvider (a == null ? null : AgentPojo.builder (a));
    }

    @OverridingMethodsMustInvokeSuper
    public void checkConsistency ()
    {
      if (m_eResponseOption == null)
        throw new IllegalStateException ("Response Layout MUST must be present");
      if (m_eResponseStatus == null)
        throw new IllegalStateException ("Response Status MUST be present");
      if (m_eResponseStatus != ERegRepResponseStatus.SUCCESS && m_eResponseStatus != ERegRepResponseStatus.FAILURE)
        throw new IllegalStateException ("Response Status MUST be SUCCESS or FAILURE");
      if (StringHelper.hasNoText (m_sRequestID))
        throw new IllegalStateException ("Request ID MUST be present");
      if (StringHelper.hasNoText (m_sSpecificationIdentifier))
        throw new IllegalStateException ("SpecificationIdentifier MUST be present");
      if (m_aIssueDateTime == null)
        throw new IllegalStateException ("Issue Date Time MUST be present");
      if (m_aDataProvider == null)
        throw new IllegalStateException ("Data Provider MUST be present");
    }

    @Nonnull
    public abstract EDMResponse build ();
  }

  /**
   * A builder for Concept responses. Contains exactly 1 response.
   *
   * @author Philip Helger
   */
  public static class BuilderConcept extends AbstractBuilder <BuilderConcept>
  {
    private String m_sRegistryObjectID;
    private final ICommonsList <ConceptPojo> m_aConcepts = new CommonsArrayList <> ();

    protected BuilderConcept ()
    {
      // Always inline responses
      super (EToopResponseOptionType.INLINE);
    }

    @Nonnull
    public BuilderConcept registryObjectID (@Nullable final String s)
    {
      m_sRegistryObjectID = s;
      return this;
    }

    @Nonnull
    public BuilderConcept randomRegistryObjectID ()
    {
      return registryObjectID (UUID.randomUUID ().toString ());
    }

    @Nonnull
    public BuilderConcept addConcept (@Nullable final Consumer <? super ConceptPojo.Builder> a)
    {
      if (a != null)
      {
        final ConceptPojo.Builder aBuilder = ConceptPojo.builder ();
        a.accept (aBuilder);
        addConcept (aBuilder.build ());
      }
      return this;
    }

    @Nonnull
    public BuilderConcept addConcept (@Nullable final CCCEVConceptType a)
    {
      return addConcept (a == null ? null : ConceptPojo.builder (a));
    }

    @Nonnull
    public BuilderConcept addConcept (@Nullable final ConceptPojo.Builder a)
    {
      return addConcept (a == null ? null : a.build ());
    }

    @Nonnull
    public BuilderConcept addConcept (@Nullable final ConceptPojo a)
    {
      if (a != null)
        m_aConcepts.add (a);
      return this;
    }

    @Nonnull
    public BuilderConcept concept (@Nullable final Consumer <? super ConceptPojo.Builder> a)
    {
      if (a != null)
      {
        final ConceptPojo.Builder aBuilder = ConceptPojo.builder ();
        a.accept (aBuilder);
        concept (aBuilder.build ());
      }
      return this;
    }

    @Nonnull
    public BuilderConcept concept (@Nullable final CCCEVConceptType a)
    {
      return concept (a == null ? null : ConceptPojo.builder (a));
    }

    @Nonnull
    public BuilderConcept concept (@Nullable final ConceptPojo.Builder a)
    {
      return concept (a == null ? null : a.build ());
    }

    @Nonnull
    public BuilderConcept concept (@Nullable final ConceptPojo a)
    {
      if (a != null)
        m_aConcepts.set (a);
      else
        m_aConcepts.clear ();
      return this;
    }

    @Nonnull
    public BuilderConcept concepts (@Nullable final ConceptPojo... a)
    {
      m_aConcepts.setAll (a);
      return this;
    }

    @Nonnull
    public BuilderConcept concepts (@Nullable final Iterable <? extends ConceptPojo> a)
    {
      m_aConcepts.setAll (a);
      return this;
    }

    @Nonnull
    public <T> BuilderConcept concepts (@Nullable final Iterable <? extends T> a, @Nonnull final Function <? super T, ConceptPojo> aMapper)
    {
      m_aConcepts.setAllMapped (a, aMapper);
      return thisAsT ();
    }

    @Override
    public void checkConsistency ()
    {
      super.checkConsistency ();

      if (StringHelper.hasNoText (m_sRegistryObjectID))
        throw new IllegalStateException ("RegistryObjectID MUST be present");
      if (m_aConcepts.isEmpty ())
        throw new IllegalStateException ("At least one Concept MUST be contained");
    }

    @Override
    @Nonnull
    public EDMResponse build ()
    {
      checkConsistency ();

      // Build the ResponseObjectPojo
      final ICommonsList <IEDMResponsePayloadProvider> aResponseObjects = new CommonsArrayList <> ();
      aResponseObjects.add (new EDMResponsePayloadConcepts (m_sRegistryObjectID, m_aConcepts));

      return new EDMResponse (m_eResponseOption,
                              m_eResponseStatus,
                              m_sRequestID,
                              m_sSpecificationIdentifier,
                              m_aIssueDateTime,
                              m_aDataProvider,
                              aResponseObjects);
    }
  }

  /**
   * A builder for document responses. Contains 1-n payloads.
   *
   * @author Philip Helger
   */
  public static class BuilderDocument extends AbstractBuilder <BuilderDocument>
  {
    private final ICommonsList <ResponseDocumentPojo> m_aResponseObjects = new CommonsArrayList <> ();

    protected BuilderDocument ()
    {
      // Always inline responses
      super (EToopResponseOptionType.INLINE);
    }

    @Nonnull
    public BuilderDocument addResponseObject (@Nullable final Consumer <? super ResponseDocumentPojo.Builder> a)
    {
      if (a != null)
      {
        // RegistryObject ID not relevant for inline responses
        final ResponseDocumentPojo.Builder aBuilder = ResponseDocumentPojo.builder ().randomRegistryObjectID ();
        a.accept (aBuilder);
        addResponseObject (aBuilder);
      }
      return this;
    }

    @Nonnull
    public BuilderDocument addResponseObject (@Nullable final ExtrinsicObjectType a)
    {
      return addResponseObject (a == null ? null : ResponseDocumentPojo.builder (a));
    }

    @Nonnull
    public BuilderDocument addResponseObject (@Nullable final ResponseDocumentPojo.Builder a)
    {
      return addResponseObject (a == null ? null : a.build ());
    }

    @Nonnull
    public BuilderDocument addResponseObject (@Nullable final ResponseDocumentPojo a)
    {
      if (a != null)
        m_aResponseObjects.add (a);
      return this;
    }

    @Nonnull
    public BuilderDocument responseObject (@Nullable final Consumer <? super ResponseDocumentPojo.Builder> a)
    {
      if (a != null)
      {
        // RegistryObject ID not relevant for inline responses
        final ResponseDocumentPojo.Builder aBuilder = ResponseDocumentPojo.builder ().randomRegistryObjectID ();
        a.accept (aBuilder);
        responseObject (aBuilder);
      }
      return this;
    }

    @Nonnull
    public BuilderDocument responseObject (@Nullable final ExtrinsicObjectType a)
    {
      return responseObject (a == null ? null : ResponseDocumentPojo.builder (a));
    }

    @Nonnull
    public BuilderDocument responseObject (@Nullable final ResponseDocumentPojo.Builder a)
    {
      return responseObject (a == null ? null : a.build ());
    }

    @Nonnull
    public BuilderDocument responseObject (@Nullable final ResponseDocumentPojo a)
    {
      if (a != null)
        m_aResponseObjects.set (a);
      else
        m_aResponseObjects.clear ();
      return this;
    }

    @Nonnull
    public BuilderDocument responseObjects (@Nullable final ResponseDocumentPojo... a)
    {
      m_aResponseObjects.setAll (a);
      return this;
    }

    @Nonnull
    public BuilderDocument responseObjects (@Nullable final Iterable <? extends ResponseDocumentPojo> a)
    {
      m_aResponseObjects.setAll (a);
      return this;
    }

    @Nonnull
    public <T> BuilderDocument responseObjects (@Nullable final Iterable <? extends T> a,
                                                @Nonnull final Function <? super T, ResponseDocumentPojo> aMapper)
    {
      m_aResponseObjects.setAllMapped (a, aMapper);
      return this;
    }

    @Override
    public void checkConsistency ()
    {
      super.checkConsistency ();

      if (m_aResponseObjects.isEmpty ())
        throw new IllegalStateException ("Response Object MUST be present");
    }

    @Override
    @Nonnull
    public EDMResponse build ()
    {
      checkConsistency ();

      return new EDMResponse (m_eResponseOption,
                              m_eResponseStatus,
                              m_sRequestID,
                              m_sSpecificationIdentifier,
                              m_aIssueDateTime,
                              m_aDataProvider,
                              m_aResponseObjects);
    }
  }

  public static class BuilderDocumentReference extends AbstractBuilder <BuilderDocumentReference>
  {
    private final ICommonsList <ResponseDocumentReferencePojo> m_aResponseObjects = new CommonsArrayList <> ();

    protected BuilderDocumentReference ()
    {
      // Always object references
      super (EToopResponseOptionType.REFERENCE);
    }

    @Nonnull
    public BuilderDocumentReference addResponseObject (@Nullable final Consumer <? super ResponseDocumentReferencePojo.Builder> a)
    {
      if (a != null)
      {
        final ResponseDocumentReferencePojo.Builder aBuilder = ResponseDocumentReferencePojo.builder ();
        a.accept (aBuilder);
        addResponseObject (aBuilder);
      }
      return this;
    }

    @Nonnull
    public BuilderDocumentReference addResponseObject (@Nullable final ObjectRefType a)
    {
      return addResponseObject (a == null ? null : ResponseDocumentReferencePojo.builder (a));
    }

    @Nonnull
    public BuilderDocumentReference addResponseObject (@Nullable final ResponseDocumentReferencePojo.Builder a)
    {
      return addResponseObject (a == null ? null : a.build ());
    }

    @Nonnull
    public BuilderDocumentReference addResponseObject (@Nullable final ResponseDocumentReferencePojo a)
    {
      if (a != null)
        m_aResponseObjects.add (a);
      return this;
    }

    @Nonnull
    public BuilderDocumentReference responseObject (@Nullable final Consumer <? super ResponseDocumentReferencePojo.Builder> a)
    {
      if (a != null)
      {
        final ResponseDocumentReferencePojo.Builder aBuilder = ResponseDocumentReferencePojo.builder ();
        a.accept (aBuilder);
        responseObject (aBuilder);
      }
      return this;
    }

    @Nonnull
    public BuilderDocumentReference responseObject (@Nullable final ObjectRefType a)
    {
      return responseObject (a == null ? null : ResponseDocumentReferencePojo.builder (a));
    }

    @Nonnull
    public BuilderDocumentReference responseObject (@Nullable final ResponseDocumentReferencePojo.Builder a)
    {
      return responseObject (a == null ? null : a.build ());
    }

    @Nonnull
    public BuilderDocumentReference responseObject (@Nullable final ResponseDocumentReferencePojo a)
    {
      if (a != null)
        m_aResponseObjects.set (a);
      else
        m_aResponseObjects.clear ();
      return this;
    }

    @Nonnull
    public BuilderDocumentReference responseObjects (@Nullable final ResponseDocumentReferencePojo... a)
    {
      m_aResponseObjects.setAll (a);
      return this;
    }

    @Nonnull
    public BuilderDocumentReference responseObjects (@Nullable final Iterable <? extends ResponseDocumentReferencePojo> a)
    {
      m_aResponseObjects.setAll (a);
      return this;
    }

    @Nonnull
    public <T> BuilderDocumentReference responseObjects (@Nullable final Iterable <? extends T> a,
                                                         @Nonnull final Function <? super T, ResponseDocumentReferencePojo> aMapper)
    {
      m_aResponseObjects.setAllMapped (a, aMapper);
      return this;
    }

    @Override
    public void checkConsistency ()
    {
      super.checkConsistency ();

      if (m_aResponseObjects.isEmpty ())
        throw new IllegalStateException ("Response Object MUST be present");
    }

    @Override
    @Nonnull
    public EDMResponse build ()
    {
      checkConsistency ();

      return new EDMResponse (m_eResponseOption,
                              m_eResponseStatus,
                              m_sRequestID,
                              m_sSpecificationIdentifier,
                              m_aIssueDateTime,
                              m_aDataProvider,
                              m_aResponseObjects);
    }

  }

  private static void _applySlots (@Nonnull final SlotType aSlot, @Nonnull final AbstractBuilder <?> aBuilder)
  {
    final String sName = aSlot.getName ();
    final ValueType aSlotValue = aSlot.getSlotValue ();
    switch (sName)
    {
      case SlotSpecificationIdentifier.NAME:
        if (aSlotValue instanceof StringValueType)
        {
          final String sValue = ((StringValueType) aSlotValue).getValue ();
          aBuilder.specificationIdentifier (sValue);
        }
        break;
      case SlotIssueDateTime.NAME:
        if (aSlotValue instanceof DateTimeValueType)
        {
          final LocalDateTime aCal = ((DateTimeValueType) aSlotValue).getValueLocal ();
          aBuilder.issueDateTime (aCal);
        }
        break;
      case SlotDataProvider.NAME:
        if (aSlotValue instanceof AnyValueType)
        {
          final Node aAny = (Node) ((AnyValueType) aSlotValue).getAny ();
          aBuilder.dataProvider (AgentPojo.builder (new AgentMarshaller ().read (aAny)));
        }
        break;
      default:
        throw new IllegalStateException ("Found unsupported slot '" + sName + "'");
    }
  }

  private static void _applyConceptSlots (@Nonnull final SlotType aSlot, @Nonnull final BuilderConcept aBuilder)
  {
    final String sName = aSlot.getName ();
    final ValueType aSlotValue = aSlot.getSlotValue ();
    switch (sName)
    {
      case SlotConceptValues.NAME:
        if (aSlotValue instanceof CollectionValueType)
        {
          final List <ValueType> aElements = ((CollectionValueType) aSlotValue).getElement ();
          for (final ValueType aElement : aElements)
            if (aElement instanceof AnyValueType)
            {
              final Object aElementValue = ((AnyValueType) aElement).getAny ();
              if (aElementValue instanceof Node)
                aBuilder.addConcept (new ConceptMarshaller ().read ((Node) aElementValue));
            }
        }
        break;
      default:
        throw new IllegalStateException ("Found unsupported slot '" + sName + "'");
    }
  }

  @Nonnull
  public static EDMResponse create (@Nonnull final QueryResponse aQueryResponse)
  {
    ValueEnforcer.notNull (aQueryResponse, "QueryResponse");

    // Get common stuff
    final ERegRepResponseStatus eResponseStatus = ERegRepResponseStatus.getFromIDOrNull (aQueryResponse.getStatus ());
    if (eResponseStatus == null)
      throw new IllegalStateException ("Unsupported query response status '" + aQueryResponse.getStatus () + "' present.");

    final String sRequestID = aQueryResponse.getRequestId ();

    // Check references
    final ObjectRefListType aObjectRefList = aQueryResponse.getObjectRefList ();
    if (aObjectRefList != null && aObjectRefList.hasObjectRefEntries ())
    {
      // Document Reference
      final BuilderDocumentReference aRealBuilder = builderDocumentReference ().responseStatus (eResponseStatus).requestID (sRequestID);
      for (final SlotType aSlot : aQueryResponse.getSlot ())
        _applySlots (aSlot, aRealBuilder);

      for (final ObjectRefType aOR : aObjectRefList.getObjectRef ())
        aRealBuilder.addResponseObject (aOR);
      return aRealBuilder.build ();
    }

    // Check inline
    final RegistryObjectListType aRegistryObjectList = aQueryResponse.getRegistryObjectList ();
    if (aRegistryObjectList != null && aRegistryObjectList.hasRegistryObjectEntries ())
    {
      if (aRegistryObjectList.getRegistryObject ().size () == 1 &&
          aRegistryObjectList.getRegistryObjectAtIndex (0).getSlotCount () == 1 &&
          SlotConceptValues.NAME.equals (aRegistryObjectList.getRegistryObjectAtIndex (0).getSlotAtIndex (0).getName ()))
      {
        // It's a Concept Response
        final RegistryObjectType aRO = aRegistryObjectList.getRegistryObject ().get (0);
        final BuilderConcept aRealBuilder = builderConcept ().responseStatus (eResponseStatus)
                                                             .requestID (sRequestID)
                                                             .registryObjectID (aRO.getId ());

        // Apply top-level response slots
        for (final SlotType aSlot : aQueryResponse.getSlot ())
          _applySlots (aSlot, aRealBuilder);

        // Read main concepts
        for (final SlotType aSlot : aRO.getSlot ())
          _applyConceptSlots (aSlot, aRealBuilder);

        return aRealBuilder.build ();
      }

      // It's a Document Response
      final BuilderDocument aRealBuilder = builderDocument ().responseStatus (eResponseStatus).requestID (sRequestID);

      // Apply top-level response slots
      for (final SlotType aSlot : aQueryResponse.getSlot ())
        _applySlots (aSlot, aRealBuilder);

      for (final RegistryObjectType aRO : aRegistryObjectList.getRegistryObject ())
        if (aRO instanceof ExtrinsicObjectType)
          aRealBuilder.addResponseObject ((ExtrinsicObjectType) aRO);

      return aRealBuilder.build ();
    }

    throw new IllegalStateException ("Found neither inline nor reference content in the response. Is it eventually an Error Response?");
  }
}
