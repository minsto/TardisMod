package tardis.common.tileents.extensions;

import io.darkcraft.darkcore.mod.helpers.WorldHelper;

import java.util.EnumSet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tardis.api.IArtronEnergyProvider;

public class LabRecipe
{
	public final ItemStack[] source;
	public final ItemStack[] dest;
	public final EnumSet<LabFlag> flags;
	public final int energyCost;

	public LabRecipe(ItemStack _source, ItemStack _dest, EnumSet<LabFlag> _flags, int _enCost)
	{
		this(new ItemStack[]{_source}, new ItemStack[]{_dest}, _flags, _enCost);
	}

	public LabRecipe(ItemStack[] _source, ItemStack[] _dest, EnumSet<LabFlag> _flags, int _enCost)
	{
		source = _source;
		dest = _dest;
		flags = _flags;
		energyCost = _enCost;
	}

	public boolean isValid()
	{
		if(energyCost < 0)
			return false;
		if((source == null) || (dest == null) || (flags == null))
			return false;
		if((source.length == 0) || (dest.length == 0) || (source.length > 5) || (dest.length > 5))
			return false;
		for(int i = 0;i<source.length;i++)
			if(source[i] == null)
				return false;
		for(int i = 0;i<dest.length;i++)
			if(dest[i] == null)
				return false;
		if(flags.contains(LabFlag.NOTINFLIGHT) && (flags.contains(LabFlag.INFLIGHT) || flags.contains(LabFlag.INCOORDINATEDFLIGHT) || flags.contains(LabFlag.INUNCOORDINATEDFLIGHT)))
			return false;
		if(flags.contains(LabFlag.INFLIGHT) && (flags.contains(LabFlag.INCOORDINATEDFLIGHT) || flags.contains(LabFlag.INUNCOORDINATEDFLIGHT)))
			return false;
		if(flags.contains(LabFlag.INCOORDINATEDFLIGHT) && flags.contains(LabFlag.INUNCOORDINATEDFLIGHT))
			return false;
		return true;
	}

	public boolean containsItemStack(ItemStack is)
	{
		if(is == null)
			return true;
		return containsItem(is.getItem());
	}

	public boolean containsItem(Item i)
	{
		for(ItemStack is: source)
		{
			if((is != null) && is.getItem().equals(i))
				return true;
		}
		return false;
	}

	public boolean isSatisfied(ItemStack[] items)
	{
		if(items == null)
			return false;
		for(ItemStack is : source)
		{
			if(is == null)
				continue;
			boolean found = false;
			for(ItemStack compIS : items)
			{
				if(compIS == null)
					continue;
				if(!WorldHelper.sameItem(compIS, is))
					continue;
				if(compIS.stackSize >= is.stackSize)
				{
					found = true;
					break;
				}
			}
			if(!found)
				return false;
		}
		return true;
	}

	public boolean flagsSatisfied(IArtronEnergyProvider core)
	{
		if(core == null)
			return false;
		boolean sat = true;
		for(LabFlag flag: flags)
			sat = sat && core.doesSatisfyFlag(flag);
		return sat;
	}
}
