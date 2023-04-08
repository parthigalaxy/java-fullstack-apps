package com.skill.profile.mapper;

import com.skill.profile.api.jaxb.types.SkillProfileType;
import com.skill.profile.model.Skill;
import com.skill.profile.model.SkillProfile;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SkillProfileMapperImpl implements SkillProfileMapper {

    private final DatatypeFactory datatypeFactory;

    public SkillProfileMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public SkillProfile skillProfileTypeToSkillProfile(SkillProfileType skillProfileType) {
        if ( skillProfileType == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String associateId = null;
        String mobile = null;
        String email = null;
        Set<Skill> skillLevals = null;
        Set<Skill> nonTechnicalSkillLevals = null;
        Date updateTime = null;
        String updatedBy = null;

        if ( null != id && !"".equals(id) ) {
            id = skillProfileType.getId();
        }
        else {
            id = UUID.randomUUID().toString();
        }
        name = skillProfileType.getName();
        associateId = skillProfileType.getAssociateId();
        mobile = skillProfileType.getMobile();
        email = skillProfileType.getEmail();
        skillLevals = skillListToSkillSet( skillProfileType.getSkillLevals() );
        nonTechnicalSkillLevals = skillListToSkillSet( skillProfileType.getNonTechnicalSkillLevals() );
        updateTime = xmlGregorianCalendarToDate( skillProfileType.getUpdateTime() );
        updatedBy = skillProfileType.getUpdatedBy();

        SkillProfile skillProfile = new SkillProfile( id, name, associateId, mobile, email, skillLevals, nonTechnicalSkillLevals, updateTime, updatedBy );

        return skillProfile;
    }

    @Override
    public SkillProfileType skillProfileToSkillProfileType(SkillProfile skillProfile) {
        if ( skillProfile == null ) {
            return null;
        }

        SkillProfileType skillProfileType = new SkillProfileType();

        skillProfileType.setId( skillProfile.getId() );
        skillProfileType.setName( skillProfile.getName() );
        skillProfileType.setAssociateId( skillProfile.getAssociateId() );
        skillProfileType.setMobile( skillProfile.getMobile() );
        skillProfileType.setEmail( skillProfile.getEmail() );
        skillProfileType.setUpdateTime( dateToXmlGregorianCalendar( skillProfile.getUpdateTime() ) );
        skillProfileType.setUpdatedBy( skillProfile.getUpdatedBy() );
        if ( skillProfileType.getSkillLevals() != null ) {
            List<com.skill.profile.api.jaxb.types.Skill> list = skillSetToSkillList( skillProfile.getSkillLevals() );
            if ( list != null ) {
                skillProfileType.getSkillLevals().addAll( list );
            }
        }
        if ( skillProfileType.getNonTechnicalSkillLevals() != null ) {
            List<com.skill.profile.api.jaxb.types.Skill> list1 = skillSetToSkillList( skillProfile.getNonTechnicalSkillLevals() );
            if ( list1 != null ) {
                skillProfileType.getNonTechnicalSkillLevals().addAll( list1 );
            }
        }

        return skillProfileType;
    }

    private static Date xmlGregorianCalendarToDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return xcal.toGregorianCalendar().getTime();
    }

    private XMLGregorianCalendar dateToXmlGregorianCalendar( Date date ) {
        if ( date == null ) {
            return null;
        }

        GregorianCalendar c = new GregorianCalendar();
        c.setTime( date );
        return datatypeFactory.newXMLGregorianCalendar( c );
    }

    protected Skill skillToSkill(com.skill.profile.api.jaxb.types.Skill skill) {
        if ( skill == null ) {
            return null;
        }

        Skill skill1 = new Skill();

        skill1.setSkill( skill.getSkill() );
        skill1.setLevel( skill.getLevel() );

        return skill1;
    }

    protected Set<Skill> skillListToSkillSet(List<com.skill.profile.api.jaxb.types.Skill> list) {
        if ( list == null ) {
            return null;
        }

        Set<Skill> set = new LinkedHashSet<Skill>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( com.skill.profile.api.jaxb.types.Skill skill : list ) {
            set.add( skillToSkill( skill ) );
        }

        return set;
    }

    protected com.skill.profile.api.jaxb.types.Skill skillToSkill1(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        com.skill.profile.api.jaxb.types.Skill skill1 = new com.skill.profile.api.jaxb.types.Skill();

        skill1.setSkill( skill.getSkill() );
        skill1.setLevel( skill.getLevel() );

        return skill1;
    }

    protected List<com.skill.profile.api.jaxb.types.Skill> skillSetToSkillList(Set<Skill> set) {
        if ( set == null ) {
            return null;
        }

        List<com.skill.profile.api.jaxb.types.Skill> list = new ArrayList<com.skill.profile.api.jaxb.types.Skill>( set.size() );
        for ( Skill skill : set ) {
            list.add( skillToSkill1( skill ) );
        }

        return list;
    }
}
