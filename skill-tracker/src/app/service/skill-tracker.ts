export interface SkillTracker{

    id: string;
    name: string;
    associateId: string;
    mobile: string;
    email: string;
    skillLevals: Skill[];
    nonTechnicalSkillLevals: Skill[];
    updateTime: Date;
    updatedBy: string;

}

export interface Skill{

    skill: string;
    level: number;

}

export interface User{
    name: string;
    role: string;
    status: boolean;
}